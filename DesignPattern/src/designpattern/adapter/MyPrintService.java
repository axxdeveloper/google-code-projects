
package designpattern.adapter;

public class MyPrintService {

    private UnknownSourcePrinter printer = new UnknownSourcePrinter();

    public void print(String text) {
        printer.print( text );
    }

}
