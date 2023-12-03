package com.example.myjavafxapp.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Lớp CreateToken cung cấp phương thức để tạo token sử dụng trong các yêu cầu của Google Translate.
 */
public class CreateToken {

    /**
     * Tạo một token cho văn bản được chỉ định sử dụng thuật toán Google Translate.
     *
     * @param text Văn bản cho mà token được tạo.
     * @return Token được tạo.
     */
    static String createToken(String text) {
        int[] tkk = TKK();
        int b = tkk[0];
        int e = 0;
        int f = 0;
        List<Integer> d = new ArrayList<>();
        for (; f < text.length(); f++) {
            int g = text.charAt(f);
            if (0x80 > g) {
                d.add(e++, g);
            } else {
                if (0x800 > g) {
                    d.add(e++, g >> 6 | 0xC0);
                } else {
                    if (0xD800 == (g & 0xFC00) && f + 1 < text.length() && 0xDC00 == (text.charAt(f + 1) & 0xFC00)) {
                        g = 0x10000 + ((g & 0x3FF) << 10) + (text.charAt(++f) & 0x3FF);
                        d.add(e++, g >> 18 | 0xF0);
                        d.add(e++, g >> 12 & 0x3F | 0x80);
                    } else {
                        d.add(e++, g >> 12 | 0xE0);
                        d.add(e++, g >> 6 & 0x3F | 0x80);
                    }
                }
                d.add(e++, g & 63 | 128);
            }
        }

        int a_i = b;
        for (e = 0; e < d.size(); e++) {
            a_i += d.get(e);
            a_i = RL(a_i, "+-a^+6");
        }
        a_i = RL(a_i, "+-3^+b+-f");
        a_i ^= tkk[1];
        long a_l;
        if (0 > a_i) {
            a_l = 0x80000000L + (a_i & 0x7FFFFFFF);
        } else {
            a_l = a_i;
        }
        a_l %= (long) Math.pow(10, 6);
        return String.format(Locale.US, "%d.%d", a_l, a_l ^ b);
    }

    /**
     * Lấy giá trị TKK sử dụng trong thuật toán tạo token.
     *
     * @return Mảng chứa giá trị TKK.
     */
    private static int[] TKK() {
        return new int[]{0x6337E, 0x217A58DC + 0x5AF91132};
    }

    /**
     * Dịch phải 32 bit cho một số nguyên.
     *
     * @param x    Số nguyên cần dịch phải.
     * @param bits Số bit dịch phải.
     * @return Số nguyên sau khi dịch phải.
     */
    private static int shr32(int x, int bits) {
        if (x < 0) {
            long xL = 0xffffffffL + x + 1;
            return (int) (xL >> bits);
        }
        return x >> bits;
    }

    /**
     * Hàm thực hiện việc xoay vòng bit trái theo chuỗi b.
     *
     * @param a Số nguyên cần thực hiện xoay bit.
     * @param b Chuỗi chứa thông tin về cách thực hiện xoay bit.
     * @return Số nguyên sau khi thực hiện xoay bit.
     */
    private static int RL(int a, String b) {
        for (int c = 0; c < b.length() - 2; c += 3) {
            int d = b.charAt(c + 2);
            d = d >= 65 ? d - 87 : d - 48;
            d = b.charAt(c + 1) == '+' ? shr32(a, d) : (a << d);
            a = b.charAt(c) == '+' ? (a + (d)) : a ^ d;
        }
        return a;
    }
}
