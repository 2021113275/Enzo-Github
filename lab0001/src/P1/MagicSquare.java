package P1;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class MagicSquare {
	public static void main(String[] args) throws IOException
    {
    	for(char i='1';i<='5';i++)
    	{
    		System.out.print(i + " " + String.valueOf(isLegalMagicSquare("src/p1/txt/" + i + ".txt")));
    		System.out.println();
    	}
//        String inputFileName = "";
//        int[][] square = readSquare(inputFileName);
//        print(square);
//        System.out.println(isLegalMagicSquare(inputFileName));
        String newFileName = "src/p1/txt/6.txt";
        writeSquare(generateMagicSquare(12),newFileName);
        System.out.println(isLegalMagicSquare(newFileName));
    }

    public static boolean isLegalMagicSquare(String fileName) throws IOException{
        //首先先把文件中的字符串读到数组中
        int[][] square;
        //若是空文件数组则无需判断,直接返回false
        if((readSquare(fileName)) == null)
        {
        	return false;
        }
        square = readSquare(fileName);
        int i, j, sum = 0, sum1, n = square.length;
        for(i = 0; i < n; i++) // 计算sum
            sum += square[0][i];

        for(i = 0; i < n; i++){ // 计算行
            sum1 = 0;
            for(j = 0; j < n; j++)
                sum1 += square[i][j];
            if(sum1 != sum)
                return false;
        }

        for(j = 0; j < n; j++){ // 计算列
            sum1 = 0;
            for(i = 0; i < n; i++)
                sum1 += square[i][j];
            if(sum1 != sum)
                return false;
        }

        sum1 = 0;
        for(i = 0; i < n; i++) // 反对角线
            sum1 += square[i][i];
        if(sum1 != sum)
            return false;

        sum1 = 0;
        for(i = 0; i < n; i++){ // 正对角线
            sum1 += square[i][n - i - 1];
        }
        return sum1 == sum;
    }
 
    public static int[][] readSquare(String fileName) throws IOException{
        File file = new File(fileName);
        if (file.length()==0) {
			return null;
		}
        int n = 1;//统计每一行有多少个数字
        
        FileReader fr = new FileReader(file);
        //输入流
        
        char c;
        while((c = (char)fr.read()) != '\n')
        {
            if(c == '\t')
            {
            	n++;
            }
        }
        fr.close();//清除上次的读取过程
        try (//特别注意每行的结尾两个字符是\n,没有\t
		FileReader fr1 = new FileReader(file)) {
			int[][] square = new int[n][n];// 获取n的值,便可以声明数组了
			int i = 0, j = 0, t;
			StringBuilder num;
			//读入全部内容
			while(i < n){
			    num = new StringBuilder();
			    t = fr1.read();
			    while(t >= 48 && t <= 57)
			    {
			    	num.append((int) t-'0');
			        t = fr1.read();
			    }
			    if(t == 13 && j != n - 1)
			    //到了一行的末尾
			    {
			        System.out.print("第" + (i + 1) + "行的元素数量不正确.");
			        return null;
			    }
			    else if(t != 9 && t != 10 && t != -1 && j != n-1){
			        System.out.print("第" + (i + 1) + "行的第" + (j + 1) + "个元素后不是'\\t'.");
			        return null;
			    }
			    try{
			        if(num.length() > 0)
			            square[i][j] = Integer.parseInt(String.valueOf(num));
			    }catch(NumberFormatException e){
			        System.out.print("第" + (i + 1) + "行第" + (j + 1) + "个元素后输入了不是数字的符号.");
			        return null;
			    }
			    if(t!=10)
			    {
			    	j++;
			    }
			    
			    if(j == n)
			    {//读完一行换一行
			        i++;
			        j=0;
			    }
			}
			fr1.close();
			return square;
		}
    }

    public static void writeSquare(int[][] square,String fileName) throws IOException{
    	if(square == null){
            System.out.println("读入的文件是空的");
            return;
        }
        FileWriter fw = new FileWriter(fileName);
        int n = square.length, i, j;
        for(i = 0; i < n; i++)
        {
            for(j = 0; j < n; j++)
            {
                fw.write(String.valueOf(square[i][j]));
                if(j < n-1)
                {
                	fw.write('\t');
                }
                if (j==n-1) 
                {
                	fw.write('\n');
				}    
            }
        }
        fw.close();
    }

    public static int[][] generateMagicSquare(int n) throws ArrayIndexOutOfBoundsException{
        
    	int[][] magic;
    	try {
			 magic = new int[n][n];
		} catch(NegativeArraySizeException e){
                System.out.println("数组的维度不能是负数");
                return null;
            }
        int row = 0, col = n / 2, i, j, square = n * n;//row:行 col:列 square:元素数量
        for(i = 1; i <= square; i++){
            try{
                magic[row][col] = i;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("数组的维度n不能是偶数");
                return null;
            }
            if(i % n == 0)// 已经到了这行最后一个,就跳到下一行
                row++;
            else{// 当i不是这行最后一个时
                if(row == 0)// 每次都把row减1,如果row已经是第一行,就把它移到最后一行
                    row = n - 1;
                else
                    row--;
                if(col == (n - 1))// 每次都把col加1,如果col已经是最后一行,就把它移到第一行
                    col = 0;
                else
                    col++;
            }
        }
        for(i = 0; i < n; i++){//打印magic数组
            for(j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");
            System.out.println();
        }
        return magic;
    }

}
