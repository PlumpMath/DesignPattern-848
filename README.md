# DesignPattern
关于项目设计模式之MVC、MVP、MVVM简单例子
MVC设计结构就是将Activity中比较耗时的操作如网络请求、数据库操作等封装抽象为模型层（model）出去简化Activity中的内容。
这种设计模式还存在问题。Activity中还存在比较多的业务逻辑和界面显示内容。我们可以再对这两部分分别封装抽取出去，如果将
逻辑业务抽取出去就是MVP模式，P表示presenter表示层。如果将界面显示抽取出去就形成了MVVM模式，VM-ViewModel是view和model
层的中间联系层，负责数据传递。
