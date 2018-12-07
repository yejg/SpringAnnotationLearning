package com.atguigu;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.atguigu.config.AppConfig;
import com.atguigu.config.RootConfig;

//web����������ʱ�򴴽����󣻵��÷�������ʼ��������ǰǰ�˿�����
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //��ȡ�������������ࣻ��Spring�������ļ���   ��������
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    //��ȡweb�����������ࣨSpringMVC�����ļ���  ��������
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    //��ȡDispatcherServlet��ӳ����Ϣ
    //  /�������������󣨰�����̬��Դ��xx.js,xx.png���������ǲ�����*.jsp��
    //  /*����������������*.jspҳ�涼���أ�jspҳ����tomcat��jsp��������ģ�
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
