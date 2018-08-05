/**
 * Created on 2018/6/12.
 *
 * @author wangxiaodong
 */
public class III
{
    int a;
    III()
    {
        System.out.println("fsdlkfjlsdk");
        this.getA();
        a=33;
        System.out.println("fsdlkfjlsdk"+a);
    }
    void getA()
    {
        System.out.println("fsdlkfj");
        System.out.println("father"+"\n"+"class"+a);
    }
    public static void main(String[] args)
    {
        C c=new C();
        //c.getA();//多加的部分
    }
}
class C extends III
{
    int a;
    C()
    {
        a=1;
    }
    void getA()
    {
        System.out.println("wo zai sub class nie");
        System.out.println("subclass"+a);
    }
}