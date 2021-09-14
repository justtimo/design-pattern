package com.wby.pattern.design.pattern.jdk8.Time;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.Format;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/10 15:44
 * @Description:
 */
public class TimeTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 时间线
         */
        //获取当前日期
        /*Instant start = Instant.now();
        Thread.sleep(2000);
        Instant end = Instant.now();
        //获取两个日期的差值
        Duration between = Duration.between(start, end);
        long millis = between.toMillis();

        Instant start2 = Instant.now();
        Thread.sleep(3122);
        Instant end2 = Instant.now();
        Duration between1 = Duration.between(start2, end2);
        //multipliedBy :乘   minus:减  isNegative:是否为负值
        boolean negative = between.multipliedBy(10).minus(between1).isNegative();
        System.out.println(negative);*/

        /**
         * 本地时间
         */
        LocalDate now = LocalDate.now();
        LocalDate myBirthday = LocalDate.of(1993, 11, 28);
        LocalDate womenBirthday = LocalDate.of(1991, Month.OCTOBER, 28);


        LocalDate nextYearBirthdayVersion2 = womenBirthday.plus(Period.ofYears(1));
        LocalDate nextYearBirthdayVersion3 = womenBirthday.plusYears(1);
        LocalDate wrongExampleVersion2 = womenBirthday.plus(Period.ofDays(365));
        LocalDate wrongExampleVersion3 = womenBirthday.plusDays(365);
        //两个Instant之间的时长是Duration ,而两个LocalDate之间的时长是Period,它表示的是流逝的年,月或日的数量
        System.out.println("123123");
        //加减月份 :注意,1月加上1一个月并不会产生2月31号,而是会返回该月有效的最后一天
        LocalDate birthdayPlusMoth = LocalDate.of(2021,1,31).plusMonths(1);
        LocalDate birthdayMinusMoth =  LocalDate.of(2021,3,31).minusMonths(1);
        System.out.println("111111111");
        //返回指定日期是一个星期中的第几天
        int value = LocalDate.of(2021, 3, 31).getDayOfWeek().getValue();
        //注意,这里的周六周日与Calender中的不同,Calender中周六是1而周日是7
        int 周六 = LocalDate.of(2021, 9, 11).getDayOfWeek().getValue();//6
        int 周日 = LocalDate.of(2021, 9, 12).getDayOfWeek().getValue();//7
        //周一加3天是周四
        DayOfWeek plus = DayOfWeek.MONDAY.plus(3);
        System.out.println("2222222222");
        /**
         * 除了LocalDate之外,还有MonthDay,YearMonth,Year等类可以描述日期,例如12月25日(没有指定年份)就可以表示成一个MonthDay
         */
        /**
         * 日期调整
         * TemporalAdjusters
         */
        //某个月的第一个星期二,with会返回一个新的LocalDate对象,而不会修改原来的对象
        LocalDate with = LocalDate.of(2021, 9, 7).with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        /**
         * 还可以自己实现TemporalAdjuster接口来创建自己的调整器:下例:计算下一个工作日
         * lambda表达式的参数类型为Temporal,他必须强转为LocalDate.
         */
        TemporalAdjuster  workDay= new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal w) {
                LocalDate result = (LocalDate) w;
                do {
                    result.plusDays(1);
                } while (result.getDayOfWeek().getValue() >= 6);
                return result;
            }
        };
        /**
         * 可以使用ofDateAdjuster方法避免强转,该方法的参数类型是UnaryOperator
         */
        TemporalAdjuster workDayVersion2 = TemporalAdjusters.ofDateAdjuster(new UnaryOperator<LocalDate>() {
            @Override
            public LocalDate apply(LocalDate localDate) {
                LocalDate result = localDate;
                do {
                    result.plusDays(1);
                } while (result.getDayOfWeek().getValue() >= 6);
                return result;
            }
        });
        System.out.println("33333333");

        /**
         * LocalTime表示当日时刻
         */
        LocalTime nowTime = LocalTime.now();
        LocalTime bedTime = LocalTime.of(23, 00);
        LocalTime bedTimeVersion2 = LocalTime.of(23, 10, 00);
        //plus和minus是按照一天24小时循环操作的
        //注意: LocalTIme不会关心是AM/PM,这种问题将交给格式器解决
        LocalTime 尿尿时间 = bedTime.plusHours(7);
        System.out.println("4444444444");
        /**
         * 本地日期和时间
         */
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("55555555");
        /**
         * 时区时间
         */
        //获取所有可用的时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        //给定时区Id获取一个ZoneId对象
        ZoneId zoneId = ZoneId.of("Asia/Chita");
        //将LocalDateTime转换为ZonedDateTime,或者使用ZonedDateTime的静态方法
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(zoneId);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(1993, 11, 18, 23, 45, 26, 0, ZoneId.of("Asia/Chita"));
        //可以将ZonedDateTime转换为其他时间日期类型
        Instant instant = zonedDateTime.toInstant();
        LocalDate localDate = zonedDateTime.toLocalDate();
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        //反之,可以使用atZone()方法获得指定时区的ZonedDateTime对象
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime3 = localDateTime.atZone(ZoneId.of("UTC"));
        //当夏令时时,时钟要向前一小时,如果构建的事件对象正好处于跳过的这一小时内,会发生什么呢?
        // 例如:2013年中欧地区的3月31日2:00切换到夏令时,如果你构建了不存在的3月31号2:30,实际得到的是3:30
        ZonedDateTime zonedDateTime4 =//2013-03-31T03:30+02:00[Europe/Berlin]
                ZonedDateTime.of(LocalDate.of(2013, 3, 31), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        //反之,夏令时结束时凌晨2:00,时钟要调慢一小时,构建这个时间段的对象,会取较早的那一个
        ZonedDateTime zonedDateTime5 =
                ZonedDateTime.of(LocalDate.of(2013, 10, 27), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        ZonedDateTime zonedDateTime6 = zonedDateTime5.plusHours(1);//2013-10-27T02:30+01:00[Europe/Berlin]
        //在调整夏令时边界的日期时要特别注意.例如,将会议设置在下个星期,不能直接加7天的Duration,而是应该使用Period类
        ZonedDateTime zonedDateTime7 =//2013-10-31T14:30-07:00[America/Los_Angeles]
                ZonedDateTime.of(LocalDate.of(2013, 10, 31), LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
        ZonedDateTime errorExaple = zonedDateTime7.plus(Duration.ofDays(7));//2013-11-07T13:30-08:00[America/Los_Angeles]
        ZonedDateTime crecttorExample = zonedDateTime7.plus(Period.ofDays(7));//2013-11-07T14:30-08:00[America/Los_Angeles]
        /**
         * OffsetDateTime:表示与UTC具有[偏移量的时间,但是没有时区规则的束缚.这个类用于专用应用,这些应用需要剔除这些规则的约束,例如某些网络协议
         */
        /**
         * 格式化和解析
         */
        //使用标准的格式器,直接调用format()
        String format = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        String format1 = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("66666666");

        /**
         * Locale格式器
         */
        //可以使用如下三种方法创建Locale格式器
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String format2 = dateTimeFormatter.format(LocalDateTime.now());
        //这些方法使用默认Locale,切换Locale使用withLocale()
        String format3 = dateTimeFormatter.withLocale(Locale.CHINA).format(LocalDateTime.now());
        //DayOfWeek和Month枚举都有getDisplay()方法,可以按照不同的Locale和格式给出星期日期和月份的名字
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL,Locale.CHINA));
        }
        //第二卷第七章有关于Locale更多的信息
        //转换为DateFormatter
        Format format4 = dateTimeFormatter.toFormat();
        //定制日期格式: 按照不断扩充的规则,每个字母都表示一个不同的时间域,而字母重复的次数对应选择的格式,见下表
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm:ss");
        String format5 = dateTimeFormatter3.format(LocalDateTime.now());
        //解析字符串中的日期时间,可以使用静态方法 parse()
        LocalDate parse = LocalDate.parse("1993-11-18");//使用标准ISO_LOCAL_DATE格式器
        //使用定制格式器
        ZonedDateTime parse1 = ZonedDateTime.parse("1993-11-11 03:32:21-0400", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        /**
         * 与遗留API的互相转换
         * Instant类似于java.util.Date. 1.8后这个类有两个额外的方法:将Date转换为Instant的toInstant()方法;以及反向转换的静态方法from()
         * ZonedDateTime 类似于java.util.GregorianCalendar,在1.8中有更细粒度的转换方法.将GregorianCalendar转换为ZonedDateTime的toZonedDateTime()
         *      静态from()方法可以执行反向转换
         * 另一个可用于日期和时间的转换集位于java.sql中.可以传递一个DateTimeFormatter给使用java.text.Format的遗留代码
         */
        Date date = new Date(System.currentTimeMillis());
        Instant instant1 = date.toInstant();
        /*LocalDateTime from = LocalDateTime.from(instant1);*/
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        Instant instant2 = Instant.ofEpochMilli(1631352062192L);
        //LocalDateTime.of(LocalDate.from(null));
        /*LocalDate from = LocalDate.from(instant2);
        LocalDateTime of = LocalDateTime.of(LocalDate.from(instant2), LocalTime.from(instant2));*/
        System.out.println("88888");
        /**
         * 打印当前月的日历
         */
        LocalDate now1 = LocalDate.now();
        Month month = now1.getMonth();
        int dayOfMonth = now1.getDayOfMonth();


    }
}
class Testttt{
    private Date birthday;

    private final StringBuilder evalutions;
    /**
     * 静态域: 每个类只有一个这样的域.
     * 每个Testtt对象都有一个自己的id域,但这个类将共享一个nextId. 即: 1000个Testttt对象,则有1000个实例域id,但是只有一个静态域nextId.即使没有一个Testtt对象,静态域nextId也存在
     * 静态域属于类,不属于任何独立的对象
     */
    private static int nextId;
    /**
     * 实例域: 每个对象对于所有的实例域都有一份自己的拷贝
     */
    private int id;

    public Date getBirthday() {
        //return birthday;  error example
        return (Date)birthday.clone();
    }

    public Testttt(Date birthday) {
        this.birthday = birthday;
        this.evalutions=new StringBuilder();
    }

    public static int getNextId(){
        return nextId;//返回静态变量
    }

    /**
     * 静态方法
     * 静态方法是没有this参数的方法(this参数即隐式参数,表示当前对象)
     * 静态方法的使用情况:
     *  1.一个方法不需要访问对象的状态,其所需参数都是通过显示参数提供(例如 Math.pow(double a, double b))
     *  2.一个方法只需要访问类的静态域(例如: Testttt.getNextId())
     *  3.工厂方法
     *      例如NumberFormat类如下使用呢工厂方法生成不同的格式化对象
     *      为什么不使用构造器完成这些呢?
     *          1.无法命名构造器.构造器名字必须与类名相同,但是这里希望货币实例 与 百分比实例采用不同的名字.
     *          2.使用构造器时,无法改变所构造的对象类型.而Factory方法将返回一个DecimalFormat类对象,这是NumberFormat的子类`
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        /**
         * 不要编写返回引用可变对象的访问器方法,例如 getBirthday()返回的Date,Date对象是可变的,这就破坏了封装性.应该使用clone()
         */
        Testttt testttt = new Testttt(new Date(System.currentTimeMillis()));
        Date birthday = testttt.getBirthday();
        System.out.println(birthday);
        birthday.setTime(19920928);
        System.out.println(birthday);

        double x=0.1;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        String format = percentInstance.format(x);
        String format1 = currencyInstance.format(x);
        System.out.println("format: "+format+"; format1: "+format1);

    }
}
class Employee{
}
class Manager extends Employee{
    public Manager() {
    }

    public Manager(int bounes) {
        this.bounes = bounes;
    }

    private int bounes;

    public int getBounes() {
        return bounes;
    }

    public void setBounes(int bounes) {
        this.bounes = bounes;
    }
}
class MnagerTest{
    @SneakyThrows
    public static void main(String[] args){
        Manager manager = new Manager(12);
        Employee[] employees = new Employee[3];
        employees[0]=manager;
        ((Manager)employees[0]).setBounes(15);

        /**
         * 反射
         */
        Class<Manager> timeTestClass = Manager.class;
        //已弃用,使用下面的方式
        //Manager timeTest1 = timeTestClass.newInstance();
        Manager timeTest = timeTestClass.getDeclaredConstructor().newInstance();

        Class<?> componentType = timeTestClass.getComponentType();

        /**
         * 带有Declared的方法: 获取这个类所有的域,方法,构造器,包括私有的.而不带的可以获取这个类和超类的 公共的域,方法,构造器
         */

        Field[] fields = timeTestClass.getFields();
        Field[] declaredFields = timeTestClass.getDeclaredFields();
        Field bounes = timeTestClass.getDeclaredField("bounes");
        boolean accessible = bounes.isAccessible();
        boolean b = bounes.canAccess(manager);
        bounes.setAccessible(true);
        boolean accessible1 = bounes.isAccessible();
        boolean b1 = bounes.canAccess(manager);
        int o = bounes.getInt(manager);
        for (Field field : fields) {
            //返回用于描述构造器,方法或域名的字符串
            String name = field.getName();
            //返回用于描述构造器,方法或域名的整型数值.使用Modifier类中的这个方法可以分析这个返回值
            int modifiers = field.getModifiers();
            //返回用于描述类中定义的构造器,方法或域的Class对象
            Class<?> declaringClass = field.getDeclaringClass();
        }

        Method[] methods = timeTestClass.getMethods();
        Method[] declaredMethods = timeTestClass.getDeclaredMethods();
        for (Method method : methods) {
            //返回用于描述构造器,方法或域名的字符串
            String name = method.getName();
            //返回用于描述构造器,方法或域名的整型数值.使用Modifier类中的这个方法可以分析这个返回值
            int modifiers = method.getModifiers();
            //返回用于描述类中定义的构造器,方法或域的Class对象
            Class<?> declaringClass = method.getDeclaringClass();
            //返回用于描述方法抛出的异常类型的Class对象数组
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            //返回一个描述参数类型的Class对象数组
            Class<?>[] parameterTypes = method.getParameterTypes();
            //返回用于描述返回类型的Class对象
            Class<?> returnType = method.getReturnType();
        }

        Constructor<?>[] constructors = timeTestClass.getConstructors();
        Constructor<?>[] declaredConstructors = timeTestClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            //返回用于描述构造器,方法或域名的字符串
            String name = constructor.getName();
            //返回用于描述构造器,方法或域名的整型数值.使用Modifier类中的这个方法可以分析这个返回值
            int modifiers = constructor.getModifiers();
            //返回用于描述类中定义的构造器,方法或域的Class对象
            Class<?> declaringClass = constructor.getDeclaringClass();
            //返回用于描述方法抛出的异常类型的Class对象数组
            Class<?>[] exceptionTypes = constructor.getExceptionTypes();
            //返回一个描述参数类型的Class对象数组
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            //

            ArrayList<Manager> list = new ArrayList<>();
            list.add(new Manager(1));
            list.add(new Manager(2));
            list.add(new Manager(3));
            list.add(new Manager(4));
            list.add(new Manager(5));
            list.removeIf(t->t!=null);

        }

    }
}
