package com.company;

import javax.swing.*;
import java.beans.*;
import java.lang.reflect.Method;

public class BeanInfo extends SimpleBeanInfo {

    BeanInfo  beanInfo;

    {
        try {
            beanInfo = (BeanInfo) Introspector.getBeanInfo(Class.forName(String.valueOf(JLabel.class)));
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ;
    public void say(String s) {
        System.out.println(s);
    }
    PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
    MethodDescriptor[] md = beanInfo.getMethodDescriptors();
    EventSetDescriptor[] evd = beanInfo.getEventSetDescriptors();

    public void properties() {
        say("Properties: ");
        for (int i = 0; i < pd.length; i++) {
            say(pd[i].getShortDescription());
            // getReadMethod i getWriteMethod return Method objects
            say(" getter: " + pd[i].getReadMethod());
            say(" setter: " + pd[i].getWriteMethod());
        }

   }

    public void methods()
    {
        say("\nMethods: ");
        for (int i=0; i < md.length; i++) {
            say(" " + md[i].getMethod());
        }
    }


    public void events()
    {
        say("\nEvents: ");
        for (int i = 0; i < evd.length; i++) {
            say("Event : " + evd[i].getShortDescription());
            Method[] met = evd[i].getListenerMethods();
            say("Handler methods: ");
            for (int j=0; j < met.length; j++)
                say(" " + met[j]);
        }
    }
}
