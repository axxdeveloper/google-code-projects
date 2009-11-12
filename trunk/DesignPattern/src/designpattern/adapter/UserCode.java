
package designpattern.adapter;

public class UserCode {

    public static void main(String[] args) {
        MyPrintService service = new MyPrintService();
        service.print( "my text" );
    }
    
}
