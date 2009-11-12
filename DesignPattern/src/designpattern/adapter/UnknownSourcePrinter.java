
package designpattern.adapter;

public class UnknownSourcePrinter {

    public void print(String text) {
        System.out.println("print with printer without source code, print text = " + text);
    }

}
