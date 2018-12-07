package com.atguigu.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

/**
 * ������
 *
 * @author lfy
 * @Aspect�� ����Spring��ǰ����һ��������
 */
@Aspect
public class LogAspects {

    //��ȡ�������������ʽ
    //1����������
    //2����������������
    @Pointcut("execution(public * com.atguigu.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    ;

    //@Before��Ŀ�귽��֮ǰ���룻�������ʽ��ָ�����ĸ��������룩
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        String[] fieldsNames = getFieldsName(joinPoint);
        Object[] args = joinPoint.getArgs();
        System.out.println("" + joinPoint.getSignature().getName() + "���С�����@Before:���������ǣ�{" + Arrays.asList(fieldsNames) + "}");
        System.out.println("" + joinPoint.getSignature().getName() + "���С�����@Before:�����б��ǣ�{" + Arrays.asList(args) + "}");
    }

    @After("com.atguigu.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "����������@After");
    }

    //JoinPointһ��Ҫ�����ڲ�����ĵ�һλ
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("" + joinPoint.getSignature().getName() + "�������ء�����@AfterReturning:���н����{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("" + joinPoint.getSignature().getName() + "�쳣�������쳣��Ϣ��{" + exception + "}");
    }

    private Map<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };

    public String[] getFieldsName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // ͨ�����ȡ�����������в������Ƶ��ַ�������
        String[] parameterNames = methodSignature.getParameterNames();
        return parameterNames;
    }

    //���ط����Ĳ����� -- ��bug
    // public String[] getFieldsName(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
    //     String classType = joinPoint.getTarget().getClass().getName();
    //     String methodName = joinPoint.getSignature().getName();
    //     Object[] args = joinPoint.getArgs();
    //     Class<?>[] classes = new Class[args.length];
    //     for (int k = 0; k < args.length; k++) {
    //         if (!args[k].getClass().isPrimitive()) {
    //             //��ȡ���Ƿ�װ���Ͷ����ǻ�������
    //             String result = args[k].getClass().getName();
    //             Class s = map.get(result);
    //             classes[k] = s == null ? args[k].getClass() : s;
    //         }
    //     }
    //     ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
    //     //��ȡָ���ķ������ڶ����������Բ���������Ϊ�˷�ֹ�����ص����󣬻�����Ҫ�������������
    //     Method method = Class.forName(classType).getMethod(methodName, classes);
    //     String[] parameterNames = pnd.getParameterNames(method);
    //     return parameterNames;
    // }

}
