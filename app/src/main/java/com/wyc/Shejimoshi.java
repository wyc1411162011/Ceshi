package com.wyc;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Date;

import com.wyc.shejimoshi.AbstractMemberStrategy;
import com.wyc.shejimoshi.AbstractRequestSuccessListener;
import com.wyc.shejimoshi.AbstractTransparentComponent;
import com.wyc.shejimoshi.AdapteePerson;
import com.wyc.shejimoshi.AdapterStudent;
import com.wyc.shejimoshi.AdapterStudent1;
import com.wyc.shejimoshi.BaomaFactory;
import com.wyc.shejimoshi.BikeFactory;
import com.wyc.shejimoshi.CommandImp;
import com.wyc.shejimoshi.ConcreteCommand;
import com.wyc.shejimoshi.DeepSheep;
import com.wyc.shejimoshi.DeptManagerHandler;
import com.wyc.shejimoshi.DirectLeaderHandler;
import com.wyc.shejimoshi.GManagerHandler;
import com.wyc.shejimoshi.HighPersonFactory;
import com.wyc.shejimoshi.ISafeCompnComponent;
import com.wyc.shejimoshi.ImplMovie;
import com.wyc.shejimoshi.Invoker;
import com.wyc.shejimoshi.LeaveRequest;
import com.wyc.shejimoshi.LowPersonFactory;
import com.wyc.shejimoshi.MemberChujiStrategy;
import com.wyc.shejimoshi.MemberGaojiStrategy;
import com.wyc.shejimoshi.MemberPriceContext;
import com.wyc.shejimoshi.Movie;
import com.wyc.shejimoshi.MyStoryInvocationHandler;
import com.wyc.shejimoshi.Person;
import com.wyc.shejimoshi.PersonDirector;
import com.wyc.shejimoshi.ProxyMovie;
import com.wyc.shejimoshi.Reciver;
import com.wyc.shejimoshi.SafeComposite;
import com.wyc.shejimoshi.SafeLeaf;
import com.wyc.shejimoshi.Sheep;
import com.wyc.shejimoshi.Sigleton;
import com.wyc.shejimoshi.SimpleFactory;
import com.wyc.shejimoshi.Singer;
import com.wyc.shejimoshi.SmartPersonBuilder;
import com.wyc.shejimoshi.TransparentComposite;
import com.wyc.shejimoshi.TransparentLeaf;
import com.wyc.shejimoshi.Wanglihong;

public class Shejimoshi {
	public static void main(String[] args) throws CloneNotSupportedException {
		//第一种设计模式单例模式 五种单例模式
		Sigleton.getInstance1();
		Sigleton.getInstance2();
		Sigleton.getInstance3();
		Sigleton.getInstance4();
		Sigleton.getInstance5();
		
		//第二种观察者模式用的很多没写在这里
		//观察者分两个角色  主题对象（Subject）和 观察者对象或者订阅者对象（Observer）当主题对象发生改变的时候
		//通知观察者对象，简单实现就是 观察者实现Listener  然后把这个listener添加到Subject里面负责管理
		//观察者对象，删除添加这些操作，然后当主题改变的时候调用Listener的方法，Android 源码如button.setOnClick
		//https://blog.csdn.net/wanggang514260663/article/details/86138144
		//第三种模式
		//3-1类适配器模式把 弄一个中间的适配器然后把源角色弄成转化成目标角色
		//目标角色就是要实现成的结果  源角色就是 之前有的东西
		//Adapter适配器角色 实现就是实现目标角色 这个是没有完成的功能 继承原角色这个是实现的
		
		AdapterStudent student=new AdapterStudent();
		student.listenToTeacherSay();
		student.study();
		//3-2对象的适配器模式 并不一定是继承关系，也可以使用多个源角色生成一个目标角色
		//BaseListAdapter android源码中的适配器模式 源觉得是list  目标角色是ListAdapter 使用Adapter 让List转成ListAdapter
		AdapterStudent1 objectStudent=new AdapterStudent1(new AdapteePerson());
		objectStudent.listenToTeacherSay();
		objectStudent.study();
		//3-3缺省接口适配器模式
		//这个是自己项目中用到的 这个是抽象类，因为用不到接口的其他两个方法，所以
		//新建一个抽象类，把其他的两个方法置空什么也不做，把用到的那个方法弄成抽象的
		//必须复写
		//比如你需要实现一个接口，但是有太多的方法，那就把这个接口集成起来，弄几个空实现，把你想要实现的那个操作
		//弄成抽象的方法就行
		AbstractRequestSuccessListener listener=new AbstractRequestSuccessListener(){

			@Override
			public void onSuccess(String message) {
				// TODO Auto-generated method stub

				
			}
			
		};
		//源码中的体现是activity 中继承Context （源角色）实现indow.Callback, KeyEvent.Callback
		//等多个目标角色
		
		//4原型模式，说白了就是克隆，为什么不用new 对象因为太耗时，比如构造方法有创建数据库什么的方法
		//再就是clone（）方法是native方法，速度很快
		//4-1浅克隆 用于所有的成员变量都是基础类型的情况，否则成员变量对应的引用用的
		//用的是一个
		Sheep qianSheep=new Sheep();
		//4-2深层克隆
		DeepSheep deepSheep=new DeepSheep();
		
		
		//5工厂模式
		//5-1* 简单工厂模式可以创建一个产品，一个工厂可以创建一个种类的，多个产品
//		 * 不符合 关闭原则，要是新加Car的时候要修改这个类，不符合
//		 * 关闭原则 ，扩展打开，修改关闭，所以引起第二种方法，工厂方法模式
		SimpleFactory.getCar(SimpleFactory.TYPE_BIKE).goWork();
		SimpleFactory.getCar(SimpleFactory.TYPE_BAOMA).goWork();
		//这种是简单工厂的另一种写法
		SimpleFactory.getBaomaCar().goWork();
		SimpleFactory.getBikeCar().goWork();
		//5-2工厂方法模式 一个工厂，创建一种产品产品，关闭原则符合，但是妈的，加一个产品，就会加一个工厂的类
		//用的代码太多，其实官方的代码也不一定完全符合各种设计模式的原则，适合最好，简单工厂模式往往是用的最多的
		new BaomaFactory().getCar().goWork();
		new BikeFactory().getCar().goWork();
		//5-3抽象工厂模式 一个工厂，可以创建多种类型的产品，跟抽象方法模式差不多，算是一个升级版本 可以生成一系列产品
		new LowPersonFactory().getCar().goWork();
		new  LowPersonFactory().getBreakfast().eat();
		
		new HighPersonFactory().getCar().goWork();
		new  HighPersonFactory().getBreakfast().eat();
		//6建造者模式 https://www.cnblogs.com/kangsir/p/6653233.html
		//https://blog.csdn.net/zhangxing52077/article/details/79462539
		//用于建造复杂的对象，创建一个对象，需要初始化他的内部对象，比如成员变量
		//Builder负责产品的创建，组装弄一些初始值也可以设置，生成对象
		//，让创建和它的表示分离（个人理解就是内部实现）
		//我的理解是先有一个具体的产品，注意是具体的，没有接口，所有就要把产品抽离出来
		//弄一个接口然后里面规定他的表示方法 建造者角色（Builder）在弄一个具体Builder类来
		//来实现这个方法 这个算是他的表示  然后在弄一个指导者（Director）这个完成他的创建，因为创建过程
		//比较复杂，然后要统一封装一下还要，因为创建多了，明显会遗漏东西
		//builder 就是 person的具体实现，就是一个person抽象出来的，并且返回person的，person
		//子里面直接就是以构造方法生产了
		//director就是person的整个构造过程，
		SmartPersonBuilder personBuilder=new SmartPersonBuilder();
		PersonDirector director=new PersonDirector();
		Person person=director.constructPerson(personBuilder);

		System.out.println(person);
		//android里面用的是Builder 创建Dialog 但是没有Dierctor 对象不算完整的
		
		//7策略者模式 网址https://blog.csdn.net/zhangliangzi/article/details/52161211
		//其实刚开始 环境角色是一个完整的功能，计算价格是个变化的功能，后来把计算价格
		//抽象成了一个接口然后成了现在的样子
		
		//策略模式属于对象的行为模式。其用意是针对一组算法，
		//将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。
		//使用的是接口隔离策略
		AbstractMemberStrategy chujiMember=new MemberChujiStrategy();
		
		AbstractMemberStrategy gaoMember=new MemberGaojiStrategy();
		MemberPriceContext chujiContex=new MemberPriceContext(chujiMember);
		//这个里面其实是 环境角色调用 策略模式这里给他提供了一个入口
		MemberPriceContext gaojiContex=new MemberPriceContext(gaoMember);
		chujiContex.jisuanPrice(100.0);
		gaojiContex.jisuanPrice(100.0);
		//android 源码中的体现是ListView.setAdapter  listView是环境角色 Adapter是策略角色
		
		//8命令模式把 调用者角色跟接受者角色隔离出来，通过一个Command 角色分开
		//命令模式可以将请求发送者和接收者完全解耦，发送者与接收者之间没有直接引用关系，
		//发送请求的对象只需要知道如何发送请求，而不必知道如何完成请求。命令模式也叫委派模式。
		Reciver reciver=new Reciver();
		CommandImp command=new ConcreteCommand(reciver);
		Invoker invoker=new Invoker(command);
		invoker.excuteCommand();
		invoker.unDoCommand();
		
		//9合成模式 这个就是整体跟部分的关系，部分在整体里面
		//允许将你的对象合成一个树形结构的整体和部分结构，合成模式让用户方便的处理 对象和对象组合
		//通俗来说就是 把对象 和对象组合抽象成一个组件角色 叶子构件角色相当于 对象 树枝构件角色相当于对象组合
		//9-1安全的合成模式 叶子构件角色和树枝构件角色不一样，但都实现了抽象构件角色的方法
		//组件角色的方法只有叶子角色和树枝角色都有的方法
		
		SafeComposite rootSafeCompnComponent=new SafeComposite("动物");
		SafeComposite dogSafeCompnComponent=new SafeComposite("狗");
		SafeComposite catSafeCompnComponent=new SafeComposite("猫");
		SafeLeaf zangaoSafeDog=new SafeLeaf("藏獒");
		SafeLeaf hashiqiSafeDog=new SafeLeaf("哮天犬");
		dogSafeCompnComponent.addChild(zangaoSafeDog);
		dogSafeCompnComponent.addChild(hashiqiSafeDog);
		SafeLeaf jiafeiSafeCat=new SafeLeaf("加菲猫");
		SafeLeaf baosiSafeCat=new SafeLeaf("波斯猫");
		catSafeCompnComponent.addChild(jiafeiSafeCat);
		catSafeCompnComponent.addChild(baosiSafeCat);
		rootSafeCompnComponent.addChild(dogSafeCompnComponent);
		rootSafeCompnComponent.addChild(catSafeCompnComponent);
		rootSafeCompnComponent.showStructure("");
		
		
		//9-2透明的合成模式
		
		AbstractTransparentComponent rootTransparentCompnComponent=new TransparentComposite("透明合成模式动物");
		AbstractTransparentComponent dogTransparentCompnComponent=new TransparentComposite("透明合成模式狗");
		AbstractTransparentComponent catTransparentCompnComponent=new TransparentComposite("透明合成模式猫");
		AbstractTransparentComponent zangaoTransparentDog=new TransparentLeaf("透明合成模式藏獒");
		AbstractTransparentComponent hashiqiTransparentDog=new TransparentLeaf("透明合成模式哮天犬");
		dogTransparentCompnComponent.addChild(zangaoTransparentDog);
		dogTransparentCompnComponent.addChild(hashiqiTransparentDog);
		AbstractTransparentComponent jiafeiTransparentCat=new TransparentLeaf("透明合成模式加菲猫");
		AbstractTransparentComponent baosiTransparentCat=new TransparentLeaf("透明合成模式波斯猫");
		catTransparentCompnComponent.addChild(jiafeiTransparentCat);
		catTransparentCompnComponent.addChild(baosiTransparentCat);
		rootTransparentCompnComponent.addChild(dogTransparentCompnComponent);
		rootTransparentCompnComponent.addChild(catTransparentCompnComponent);
		//这个就直接报错了
//		AbstractTransparentComponent baosiChildTransparentCat=new TransparentLeaf("透明合成模式波斯猫的下一代");
//		baosiTransparentCat.addChild(baosiChildTransparentCat);
		rootTransparentCompnComponent.showStructure("");
		
		//安全式：从客户端使用上看，安全式就不会发生误操作的可能。
		//透明式：从客户端使用上看，透明式是不用区分树枝对象和叶子对象。
		//合成模式的目的是让客户端不区分操作的是树枝对象还是叶子对象，而是统一的方式来操作。
		//还有，对于安全式的实现，区分树枝和叶子对象的情况下，有时需要类型强转，这种体现就不安全了。
		//所以综合考虑还是着重选择透明式合成模式的实现。当然这不是绝对的，还是要看具体需求的。
		//android 系统中的合成模式是View和ViewGroup view是抽象组件角色 ViewGroup是树枝构件角色 TextView 是叶子构件角色
		//这个是安全的组合模式（即合成模式）


		//9代理模式 通过代理访问目标对象，增加目标对象额外的功能
		//静态代理

		ImplMovie movie = new Movie();
		ImplMovie proxyMovie = new ProxyMovie(movie);
		proxyMovie.paly();



		//动态代理 运行状态下生成的一个代理
		//代理操作的类MyStoryInvocationHandler 包裹了一层被代理的类
		//当被代理的类调用方法的时候，可以特定的添加一些调用方法等操作
		Wanglihong liHong = new Wanglihong();
		//实例化调用处理类（编好的故事）
		MyStoryInvocationHandler handler = new MyStoryInvocationHandler(liHong);

		//接下来创建代理（经纪人）

		//获取目标对象的接口类对象数组

		//创建代理
		Singer proxy = (Singer) Proxy.newProxyInstance(Singer.class.getClassLoader(), new Class[]{Singer.class}, handler);

		//开始点歌
		//proxy.singSong("就是现在");
		System.out.println("****** 歌唱中......********");
		//歌唱完了，say goodBye吧


		//10 责任链模式
		//https://www.jianshu.com/p/967726f08026
		//为请求创建一个接受者对象的链条，在处理请求的时候执行过滤，各司其职
		//不一定有接受者处理请求，责任链上的处理者负责处理请求，客户只需要将请求发送到责任链即可，
		// 无须关心请求的处理细节和请求的传递，所以职责链将请求的发送者和请求的处理者解耦了
		GManagerHandler gManagerHandler=new GManagerHandler();
		DirectLeaderHandler directLeaderHandler=new DirectLeaderHandler();
		DeptManagerHandler deptManagerHandler=new DeptManagerHandler();
		deptManagerHandler.setNextHandler(gManagerHandler);
		directLeaderHandler.setNextHandler(deptManagerHandler);

		LeaveRequest request=new LeaveRequest().leaveDays(18).name("小明");
		System.out.println(request.getName()+"发起请"+request.getLeaveDays()+"天假的申请");
		directLeaderHandler.handlerRequest(request);

		LeaveRequest request2=new LeaveRequest().leaveDays(1).name("小红");
		System.out.println(request2.getName()+"发起请"+request2.getLeaveDays()+"天假的申请");
		directLeaderHandler.handlerRequest(request2);

		LeaveRequest request3=new LeaveRequest().leaveDays(2).name("小亮");
		System.out.println(request3.getName()+"发起请"+request3.getLeaveDays()+"天假的申请");
		directLeaderHandler.handlerRequest(request3);
	}
}
