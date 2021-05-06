package cn.edu.nju.connector;

public interface Converter {

    public String convert(String content);

	public String convert(String content, String config);
}