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
 * 切面类
 *
 * @author lfy
 * @Aspect： 告诉Spring当前类是一个切面类
 */
@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1、本类引用
    //2、其他的切面引用
    @Pointcut("execution(public * com.atguigu.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    ;

    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        String[] fieldsNames = getFieldsName(joinPoint);
        Object[] args = joinPoint.getArgs();
        System.out.println("" + joinPoint.getSignature().getName() + "运行。。。@Before:参数名称是：{" + Arrays.asList(fieldsNames) + "}");
        System.out.println("" + joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After("com.atguigu.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "结束。。。@After");
    }

    //JoinPoint一定要出现在参数表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("" + joinPoint.getSignature().getName() + "正常返回。。。@AfterReturning:运行结果：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("" + joinPoint.getSignature().getName() + "异常。。。异常信息：{" + exception + "}");
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
        // 通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        return parameterNames;
    }

    //返回方法的参数名 -- 有bug
    // public String[] getFieldsName(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
    //     String classType = joinPoint.getTarget().getClass().getName();
    //     String methodName = joinPoint.getSignature().getName();
    //     Object[] args = joinPoint.getArgs();
    //     Class<?>[] classes = new Class[args.length];
    //     for (int k = 0; k < args.length; k++) {
    //         if (!args[k].getClass().isPrimitive()) {
    //             //获取的是封装类型而不是基础类型
    //             String result = args[k].getClass().getName();
    //             Class s = map.get(result);
    //             classes[k] = s == null ? args[k].getClass() : s;
    //         }
    //     }
    //     ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
    //     //获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
    //     Method method = Class.forName(classType).getMethod(methodName, classes);
    //     String[] parameterNames = pnd.getParameterNames(method);
    //     return parameterNames;
    // }

}
