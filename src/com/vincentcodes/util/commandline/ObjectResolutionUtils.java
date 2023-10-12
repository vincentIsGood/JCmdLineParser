package com.vincentcodes.util.commandline;

import com.vincentcodes.util.commandline.exceptions.ConversionException;

/**
 * Conversion from String value to something else.
 */
public class ObjectResolutionUtils {
    
    public static Object resolveValue(String value, Class<?> clazz, String fieldName){
        try{
            if(clazz.isArray()){
                return resolveArrayValue(value, clazz, fieldName);
            }
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

    /**
     * @param value expect "," (without space) separated values 
     * (eg. a,b,c OR 1,2,3 OR true,true,false, etc.)
     */
    public static Object resolveArrayValue(String value, Class<?> clazz, String fieldName){
        try{
            clazz = clazz.getComponentType();
            if(clazz.equals(boolean.class) || clazz.equals(Boolean.class)){
                String[] elements = value.split(",");
                boolean[] res = new boolean[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Boolean.parseBoolean(elements[i]);
                return res;
            }else if(clazz.equals(int.class) || clazz.equals(Integer.class)){
                String[] elements = value.split(",");
                int[] res = new int[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Integer.parseInt(elements[i]);
                return res;
            }else if(clazz.equals(double.class) || clazz.equals(Double.class)){
                String[] elements = value.split(",");
                double[] res = new double[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Double.parseDouble(elements[i]);
                return res;
            }else if(clazz.equals(byte.class) || clazz.equals(Byte.class)){
                String[] elements = value.split(",");
                byte[] res = new byte[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Byte.parseByte(elements[i]);
                return res;
            }else if(clazz.equals(short.class) || clazz.equals(Short.class)){
                String[] elements = value.split(",");
                short[] res = new short[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Short.parseShort(elements[i]);
                return res;
            }else if(clazz.equals(long.class) || clazz.equals(Long.class)){
                String[] elements = value.split(",");
                long[] res = new long[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Long.parseLong(elements[i]);
                return res;
            }else if(clazz.equals(float.class) || clazz.equals(Float.class)) {
                String[] elements = value.split(",");
                float[] res = new float[elements.length];
                for(int i = 0; i < elements.length; i++)
                    res[i] = Float.parseFloat(elements[i]);
                return res;
            }else{
                return value.split(",");
            }
        }catch(NumberFormatException e){
            throw new ConversionException(String.format("Cannot parse value '%s' for field '%s'", value, fieldName));
        }
    }
}
