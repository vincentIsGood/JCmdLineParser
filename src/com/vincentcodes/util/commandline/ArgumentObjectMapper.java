package com.vincentcodes.util.commandline;

import java.lang.reflect.Field;

import com.vincentcodes.util.commandline.annotations.CmdOption;
import com.vincentcodes.util.commandline.exceptions.ConversionException;

public class ArgumentObjectMapper {
    public static boolean SHOW_SHORT_FORM_DESC = false;

    /**
     * @param args from main(String[] args))
     * @param optionsClass map options to this class
     * @return the instance of optionsClass
     */
    public static <T> ObjectMapperParseResult<T> parseToObject(String[] args, Class<T> optionsClass){
        T instance = null;
        try{
            instance = optionsClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(String.format("Cannot create class '%s'", optionsClass.getName()));
        }

        ParserConfig parserConfig = createParserConfigFromClass(optionsClass);
        CommandLineParser parser = new CommandLineParser(parserConfig);
        Command command = parser.parse(args);
        
        for(Field field : optionsClass.getDeclaredFields()){
            field.setAccessible(true);

            try{
                CmdOption optionAnno = field.getAnnotation(CmdOption.class);
                if(optionAnno == null)
                    continue;
                
                String optionValue = optionAnno.value();
                if(optionValue.trim().isEmpty()) 
                    optionValue = field.getName();

                String fullOptionName = "--" + optionValue;
                String fullShortName = optionAnno.shortForm().isEmpty()? null : "-" + optionAnno.shortForm();
                if(!command.hasOption(fullOptionName) && !command.hasOption(fullShortName)) continue;

                boolean isStandalone = field.getType().equals(boolean.class);
                if(isStandalone){
                    field.set(instance, true);
                    continue;
                }

                // short form value takes precedence
                if(!optionAnno.shortForm().isEmpty()){
                    field.set(instance, resolveValue(command.getOptionValue(fullShortName), field.getType(), field.getName()));
                    continue;
                }

                field.set(instance, resolveValue(command.getOptionValue(fullOptionName), field.getType(), field.getName()));
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(String.format("Cannot set value of field '%s'", field.getName()));
            }
        }

        return new ObjectMapperParseResult<T>(instance, parserConfig);
    }

    private static ParserConfig createParserConfigFromClass(Class<?> optionsClass){
        ParserConfig parserConfig = new ParserConfig();

        for(Field field : optionsClass.getFields()){
            field.setAccessible(true);

            CmdOption optionAnno = field.getAnnotation(CmdOption.class);
            if(optionAnno == null)
                continue;
            
            String optionValue = optionAnno.value();
            if(optionValue.trim().isEmpty()) 
                optionValue = field.getName();
            
            String fullOptionName = "--" + optionValue;
            boolean isStandalone = field.getType().equals(boolean.class);
            parserConfig.addOption(fullOptionName, isStandalone, optionAnno.parameterDescription(), optionAnno.description());
        

            String fullShortName = optionAnno.shortForm().isEmpty()? null : "-" + optionAnno.shortForm();
            if(fullShortName == null)
                continue;
            parserConfig.addOption(fullShortName, isStandalone, SHOW_SHORT_FORM_DESC? String.format("short form of '%s'", fullOptionName) : "");
        }
        return parserConfig;
    }

    private static Object resolveValue(String value, Class<?> clazz, String fieldName){
        try{
            if(clazz.equals(boolean.class) || clazz.equals(Boolean.class)){
                return Boolean.parseBoolean(value);
            }else if(clazz.equals(int.class) || clazz.equals(Integer.class)){
                return Integer.parseInt(value);
            }else if(clazz.equals(double.class) || clazz.equals(Double.class)){
                return Double.parseDouble(value);
            }else if(clazz.equals(byte.class) || clazz.equals(Byte.class)){
                return Byte.parseByte(value);
            }else if(clazz.equals(short.class) || clazz.equals(Short.class)){
                return Short.parseShort(value);
            }else if(clazz.equals(long.class) || clazz.equals(Long.class)){
                return Long.parseLong(value);
            }else if(clazz.equals(float.class) || clazz.equals(Float.class)) {
                return Float.parseFloat(value);
            }
        }catch(NumberFormatException e){
            throw new ConversionException(String.format("Cannot parse value '%s' for field '%s'", value, fieldName));
        }
        return value;
    }
}
