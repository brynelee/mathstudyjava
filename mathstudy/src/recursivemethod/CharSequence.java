package recursivemethod;

public class CharSequence {

        /**
     * 产生排列组合的递归写法
     * @param t     数组
     * @param k     起始排列值
     * @param n     数组长度
     */
    public static void pai(int[] t, int k, int n){
        if (k == n-1){//输出这个排列
            for (int i = 0; i < n; i++) {
                System.out.print(t[i] + " ");
            }
            System.out.println();
        }else {
            for (int i = k; i < n; i++) {
                int tmp = t[i]; t[i] = t[k]; t[k] = tmp;//一次挑选n个字母中的一个,和前位置替换
                pai(t, k+1, n);                      //再对其余的n-1个字母一次挑选
                tmp = t[i]; t[i] = t[k]; t[k] = tmp;    //再换回来
            }
        }
    }

    public static void main(String[] args){
        int[] t = {1, 2, 3, 4};
        //n = len(t);
        int n = 4;
        CharSequence.pai(t, 0, n);
    }

}

