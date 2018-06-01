package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyEncodeLab {
    static void demo1() {
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "guest:guest";
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
    }

    static void demo2() {

    }

    static void _main() {

    }

    static void main(String... args) {
        demo1()
    }
}
