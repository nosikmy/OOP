package ru.nsu.aramazanova1;

public class Complex {
    private Double re;
    private Double im;

    public Complex() {
        this.re = 0.0;
        this.im = 0.0;
    }

    public Double getRe() {
        return re;
    }

    public void setRe(Double re) {
        this.re = re;
    }

    public Double getIm() {
        return im;
    }

    public void setIm(Double im) {
        this.im = im;
    }

    public Double abs() {
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    public Double arg() {
        if (re > 0) {
            return Math.atan(im / re);
        } else if (re < 0) {
            if (im >= 0) {
                return Math.atan(im / re) + Math.PI;
            } else {
                return Math.atan(im / re) - Math.PI;
            }
        } else if (im < 0) {
            return Math.PI / 2;
        } else if (im > 0) {
            return -Math.PI / 2;
        } else {
            return 0.0;
        }
    }

    public Complex sum(Complex b) {
        re += b.re;
        im += b.im;
        return this;
    }

    public Complex dif(Complex b) {
        re -= b.re;
        im -= b.im;
        return this;
    }

    public Complex mult(Complex b) {
        Complex ans = new Complex();
        ans.re = re * b.re - im * b.im;
        ans.im = re * b.im + im * b.re;
        return ans;
    }

    public Complex div(Complex b) {
        Complex ans = new Complex();
        ans.re = abs() / b.abs() * Math.cos(arg() - b.arg());
        ans.im = abs() / b.abs() * Math.sin(arg() - b.arg());
        return ans;
    }

    public Complex pow(Complex b) throws Exception {
        if (b.im != 0.0) {
            throw new Exception("Ненатуральная степень");
        }
        Complex ans = new Complex();
        ans.re = Math.pow(abs(), b.re) * Math.cos(arg() * b.re);
        ans.im = Math.pow(abs(), b.re) * Math.sin(arg() * b.re);
        return ans;
    }

    public Complex log() {
        Complex ans = new Complex();
        ans.re = Math.log(abs());
        ans.im = arg();
        return ans;
    }

    public Complex sqrt() {
        Complex ans = new Complex();
        ans.re = Math.sqrt((abs() + re) / 2);
        ans.im = Math.sqrt((abs() - re) / 2) * Math.signum(im);
        return ans;
    }

    public Complex sin() {
        Complex ans = new Complex();
        ans.re = Math.sin(re) * Math.cosh(im);
        ans.im = Math.cos(re) * Math.sinh(im);
        return ans;
    }

    public Complex cos() {
        Complex ans = new Complex();
        ans.re = Math.cos(re) * Math.cosh(im);
        ans.im = Math.sin(re) * Math.sinh(im);
        return ans;
    }
}
