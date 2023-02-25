package P3;

import java.util.List;
import java.util.ArrayList;

public class Person {
	private String myname;  //��������
	private List<Person> friendofmyname;   //���˵�����
	private static ArrayList<String> hisallperson =new ArrayList<String>(); //����ȫ�����ֱ����汾���������ѵ�����

	public Person(String name) {     //���췽��
		if(hisallperson.contains(name)) {       //�����������ֱ����Ѵ���ʱ
			System.out.println("�������Ѵ���");
			System.exit(0);
		}
		else {   //��������ʱ
			this.myname=name;
			friendofmyname=new ArrayList<>();
			hisallperson.add(name);
		}
	}
	
	public void addnewfriend(Person one) {  //���ӱ��˵�������
		this.friendofmyname.add(one);
	}
	
	public String getmyname() {   //�õ���������
		return this.myname;
	}
	
	public List<Person> gethisfriend(){  //�õ����˵������б�
		return this.friendofmyname;
	}
}