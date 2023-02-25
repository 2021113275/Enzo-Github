package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FriendshipGraph {
	public List<Person> allpeople;  //�������е��˶���
	public List<String> allname;    //���������Ѿ����ڵ�����
	
	public FriendshipGraph() {   //���췽��
		allpeople=new ArrayList<Person>();
		allname=new ArrayList<String>();
	}
	
	public void addVertex(Person newpeople) {  //����һ���µ��˶���
		String name1=newpeople.getmyname();
		if(allname.contains(name1)) {    //�������Ѵ��ڣ��ظ�
			System.out.println("�����Ѵ��ڣ��ظ�");
			System.exit(0);
		}
		else {
			allname.add(name1);
			allpeople.add(newpeople);
		}
	}
	
	public void addEdge(Person a, Person b) {  //����a��һ������b
		a.addnewfriend(b);
	}
	
	public int getDistance(Person c1, Person c2) {  //�õ�������֮��ľ���
		if(c1==c2) {
			return 0;
		}
		Map<Person,Integer> theway=new HashMap<>();
		Queue<Person> myqueue=new LinkedList<>();
		myqueue.offer(c1);
		theway.put(c1,0);
		int distance;
		while(!myqueue.isEmpty()) {  //ֻҪ���в���
			Person top=myqueue.poll();
			distance=theway.get(top);
			List<Person> allfriend=top.gethisfriend();
			for(Person m:allfriend) {
				if(!theway.containsKey(m)) {
					theway.put(m,distance+1);  //��ĳ�˵�����ȫ�����Ϊ��һ����
					myqueue.offer(m);
					if(m==c2) {
						return theway.get(c2);
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		System.out.println("ԭ��ʵ�鱨��������Ӧͼ������Ϊ:");
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println("(rachel, ross)֮�����Ϊ"+graph.getDistance(rachel, ross));
		// should print 1
		System.out.println("(rachel, ben)֮�����Ϊ"+graph.getDistance(rachel, ben));
		// should print 2
		System.out.println("(rachel, rachel)֮�����Ϊ"+graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println("(rachel, kramer)֮�����Ϊ"+graph.getDistance(rachel, kramer));
		// should print -1
		
		System.out.println("\n������Ross��ΪRachel��������Ϊ:");
		ross = new Person("Rachel");
	}
}
