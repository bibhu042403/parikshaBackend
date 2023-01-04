package com.example.pariksha.utlis;

import org.springframework.beans.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ConvertionUtils {
    public static <I,O> O convertPOJO(I i, O o) {
        try {
            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            BeanUtils.copyProperties(o,i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public static <I,O> List<O> convertPOJOList(List<I> inputList, O o) {
        List<O> outputList = new ArrayList<>();

        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
        try {
            for (I i : inputList) {
//                O temp = (O) Class.forName(String.valueOf(o.getClass()));
                O temp = (O) o.getClass().newInstance();

                BeanUtils.copyProperties(temp,i);
                outputList.add(temp);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return outputList;
    }
}
