package com.ctyun.web.date;

import com.ctyun.utils.UtilFuns;
import com.ctyun.web.exception.CustomerException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hga
 * @date 2019-07-24 00:27
 */
@Component
public class DateFormat implements Converter<String,Date> {
    private String dateformat;


    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    @Override
    public Date convert(String source) {
        if (UtilFuns.isEmpty(source)){
            try {
                throw   new CustomerException("日期格式不正确");
            } catch (CustomerException e) {
                e.printStackTrace();
            }
        }else {
            if (UtilFuns.isEmpty(dateformat)){
                dateformat = "yyyy-MM-dd";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
            try {
                Date parse = dateFormat.parse(source);
                return parse;
            } catch (ParseException e) {

                e.printStackTrace();
            }

        }

        return null;
    }
}