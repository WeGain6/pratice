package test.entity;

/**
 * @ClassName: Grande
 * @Description: 成绩信息
 * @Author: XuWei
 * @Date: 2022-12-19 16:31
 */
public class Grande {

    private int stuNo;

    private String stuName;

    private String stuClass;

    private Double mathScores;

    private Double englishScores;

    private Double chineseScores;

    public Grande() {
    }

    public Grande(int stuNo, String stuName, String stuClass, Double mathScores, Double englishScores, Double chineseScores) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuClass = stuClass;
        this.mathScores = mathScores;
        this.englishScores = englishScores;
        this.chineseScores = chineseScores;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public Double getMathScores() {
        return mathScores;
    }

    public void setMathScores(Double mathScores) {
        this.mathScores = mathScores;
    }

    public Double getEnglishScores() {
        return englishScores;
    }

    public void setEnglishScores(Double englishScores) {
        this.englishScores = englishScores;
    }

    public Double getChineseScores() {
        return chineseScores;
    }

    public void setChineseScores(Double chineseScores) {
        this.chineseScores = chineseScores;
    }

    @Override
    public String toString() {
        return "Grande{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", mathScores=" + mathScores +
                ", englishScores=" + englishScores +
                ", chineseScores=" + chineseScores +
                '}';
    }
}
