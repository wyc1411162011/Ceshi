package com.wyc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Stack;

public class suanfa {
	static class Person{
		int age;
		String name;
	}
	public static void main(String[] args) {
		
		int areadSortArr[]={10,1,9,1,2,1,3,5,6,7,4,8,2,4};
		
		
		Person[]personArrPersons=new Person[areadSortArr.length] ;
		for(int i=0;i<areadSortArr.length;i++){
			int age=areadSortArr[i];
			Person person=new Person();
			person.age=age;
			if(i==1){
				person.name="永";
			}else if(i==3){
				person.name="王";
			}else if(i==5){
				person.name="超";
			}else{
				person.name="哈哈";
			}
			personArrPersons[i]=person;
		}
		int length=100000;
		int arr[]=new int[length];
		Random ra =new Random();
		for(int i=0;i<arr.length;i++){
			arr[i]=ra.nextInt(length/10);
		}
		int tempArr[]=new int[length];
		for(int i=0;i<tempArr.length;i++){
			tempArr[i]=ra.nextInt(length/10);
		}
//		insertSort(tempArr);
//		binaryInsertSort(arr);
		int []shellArr={10,1,9,1,2,1,3,5,6,7,4,8,2,4,100,4,5,6,2,7};
		HeapSort(shellArr);
		printArr(shellArr);
	}
	public static void printArr(int []arr){
		for(int value:arr){
			System.out.print(value+" ");
		}
	}
	
	public static void printArr(Object []arr){
		for(Object value:arr){
			System.out.println(value);
		}
	}
	//二分查找递归方式
	public static int binSearch(int arr[],int start,int end,int key){
		int mid=(end+start)/2;
		if(arr[mid]==key){
			return mid;
		}else{
			if(start>=end){
				//这个时候代表过界
				return -1;
			}else {
				if(arr[mid]>key){
					return binSearch(arr, start, mid-1, key);
				}else {
					return binSearch(arr, mid+1, end, key);
				}
			}
		}
		
		
	}
	//二分查找非递归
	public static int binSearch(int arr[],int key){
		int low=0;
		int high=arr.length-1;
		while(low<=high){
			int mid=(low+high)/2;
			if(arr[mid]==key){
				return mid;
			}else if(arr[mid]>key){
				high=mid-1;
			}else{
				low=mid+1;
			}
		}
		return -1;
	}
	
	
	
	
	
	//排序算法1 直接插入排序插入排序的理念是插入一个有序的数组，默认的比如
	//把这个数组的第一个值单独拎出来，弄成一新的数组，然后不断的往里面嵌入值
		public static void insertSort(int arr[]){
			long currentTime=System.currentTimeMillis();
			int length=arr.length;
			for(int i=1;i<length;i++){
				for(int j=i-1;j>=0;j--){
					if(arr[j]>arr[j+1]){
						int temp=arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=temp;
					}else{
						break;
					}
				}
			}
			System.out.println("插入排序"+(System.currentTimeMillis()-currentTime));
		}
		//排序算法2  折半快速排序
		public static void binaryInsertSort(int arr[]){
			long currentTime=System.currentTimeMillis();
			for(int i=1;i<arr.length;i++){
				int length=arr.length;
				int temp=arr[i];
				int low=0;
				int high=i-1;
				while(low<=high){
					int mid=(low+high)/2;
					if(arr[mid]>temp){
						high=mid-1;
					}else{
						low=mid+1;
					}
				}
				for(int j=i-1;j>=low;j--){
					arr[j+1]=arr[j];
				}
				arr[low]=temp;
			}
			System.out.println("二分排序"+(System.currentTimeMillis()-currentTime));
		}
		
		
		// 3  希尔排序并不稳定，O(1)的额外空间，时间复杂度为O(N*(logN)^2)。
		//希尔排序类似于一种分列，分成一个以增量为大小的列，然后这个列里面按照插入排序进行比较
		//当这个增量一点点变小最后为1了也就排好序了
		//最外层的时间复杂度是LOGN 里面是是N
		//里面每列进行插入排序也就是每个值都要给他后续的index+r 进行比较，看看是否需要互换位置
		public static void shellInsertSort(int []arr){
			long currentTime=System.currentTimeMillis();
			
			int length=arr.length;
			for(int r=length/2;r>=1;r=r/2){
				for(int i=r;i<length;i++){
					for(int j=i-r;j>=0;j=j-r){
						if(arr[j]>arr[j+r]){
							int temp=arr[j];
							arr[j]=arr[j+r];
							arr[j+r]=temp;
						}else{
							break;
						}
					}
				}
			}

			System.out.println("希尔排序"+(System.currentTimeMillis()-currentTime));
		}
		
		
		//以上三种属于插入排序
		
		//交换类排序 4冒泡排序
		//比较两个相邻的元素，将值大的元素交换至右端。
		public static void bubbleSort(int arr[]){
			int length=arr.length;
			for(int i=0;i<length-1;i++){
				for(int j=0;j<(length-1-i);j++){
					if(arr[j]>arr[j+1]){
						int temp=arr[j+1];
						arr[j+1]=arr[j];
						arr[j]=temp;
					}
				}
			}
		}
		
		//交换类排序 5快速排序 不稳定的 O(nlogn)
		//选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
		//从右面比较的时候一个个比，当比他大的情况就停止
		//通俗的解释就是 把基值往中间踢 不停的踢
		//分治法的基本思想是：将原问题分解为若干个规模更小但结构与原问题相似的子问题。递归地解这些子问题，然后将这些子问题的解组合为原问题的解。
		public static void quickSort(int []arr,int low,int high){
			int start=low;
			int end=high;
			int key=arr[low];
			while(end>start){
				//因为基值的在从第一个开始的所以，就从后到前开始
				while(end>start&&arr[end]>=key){
					end--;
				}
				//这个必须要判断一下未了防止到结束比较的时候都没有出现第一个数据
				if(arr[end]<key){
					int temp=arr[end];
					arr[end]=arr[start];
					arr[start]=temp;
				}
				while(end>start&&arr[start]<=key){
					start++;
				}
				if(arr[start]>key){
					int temp=arr[end];
					arr[end]=arr[start];
					arr[start]=temp;
				}
				//最后的结果肯定是关键的值在start==end那个时候 key
			}
			if(start>low){
				//证明左侧还有没有拍好序列的数组
				quickSort(arr, low, start-1);
			}
			
			if(end<high){
				quickSort(arr, end+1, high);
			}
			
		}
		
		//这个是不稳定的算法
		public static void quickSortPerson(Person []arr,int low,int high){
			int start=low;
			int end=high;
			int key=arr[low].age;
			while(end>start){
				while(end>start&&arr[end].age>=key){
					end--;
				}
				//这个必须要判断一下未了防止到结束比较的时候都没有出现第一个数据
				if(arr[end].age<key){
					Person person=arr[end];
					arr[end]=arr[start];
					arr[start]=person;
				}
				while(end>start&&arr[start].age<=key){
					start++;
				}
				if(arr[start].age>key){
					Person person=arr[end];
					arr[end]=arr[start];
					arr[start]=person;
				}
				//最后的结果肯定是关键的值在start==end那个时候 key
			}
			if(start>low){
				//证明左侧还有没有拍好序列的数组
				quickSortPerson(arr, low, start-1);
			}
			
			if(end<high){
				quickSortPerson(arr, end+1, high);
			}
			
		}
		//6选择算法，从 0到length-1一个数值一个数值的比较 找出最小的跟第一个换位置
		//简单选择排序是不稳定的
		//稳定的  n的平方
		//每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕
		//https://www.cnblogs.com/shen-hua/p/5424059.html
		private static void selectionSort(Person []arr){
			int length=arr.length;
			for(int i=0;i<length-1;i++){
				int smallIndex=i;
				for(int j=i+1;j<length;j++){
					if(arr[j].age<arr[smallIndex].age){
						smallIndex=j;
					}
				}
				if(smallIndex!=i){
					Person person=arr[smallIndex];
					arr[smallIndex]=arr[i];
					arr[i]=person;
				}
			}
			
		}
		
		private static void selectionSort(int []arr){
			int length=arr.length;
			for(int i=0;i<length-1;i++){
				int smallIndex=i;
				for(int j=i+1;j<length;j++){
					if(arr[j]<arr[smallIndex]){
						smallIndex=j;
					}
				}
				if(smallIndex!=i){
					int value=arr[smallIndex];
					arr[smallIndex]=arr[i];
					arr[i]=value;
				}
			}
			
		}
		
		
		public static void treeSelectSort(Object[] a){    
		       int len = a.length;  
		       int treeSize = 2 * len - 1;  //完全二叉树的节点数  
		       int low = 0;  
		       Object[] tree = new Object[treeSize];    //临时的树存储空间  
		       //由后向前填充此树，索引从0开始  
		       for(int i = len-1,j=0 ;i >= 0; --i,j++){      //填充叶子节点  
		           tree[treeSize-1-j] = a[i];  
		       }  
		         
		       for(int i = treeSize-1;i>0;i-=2){ //填充非终端节点  
		           tree[(i-1)/2] = ((Comparable)tree[i-1]).compareTo(tree[i]) < 0 ? tree[i-1]:tree[i];  
		       }  
		         
		       //不断移走最小节点   
		       int minIndex;  
		       while(low < len){  
		           Object min = tree[0];    //最小值  
		           a[low++] = min;  
		           minIndex = treeSize-1;         
		           //找到最小值的索引  
		           while(((Comparable)tree[minIndex]).compareTo(min)!=0){  
		               minIndex--;  
		           }  
		           tree[minIndex] = Integer.MAX_VALUE;  //设置一个最大值标志  
		           //找到其兄弟节点  
		           while(minIndex > 0){      //如果其还有父节点  
		               if(minIndex % 2 == 0){   //如果是右节点  
		                   tree[(minIndex-1)/2] = ((Comparable)tree[minIndex-1]).compareTo(tree[minIndex])  
		                        < 0 ? tree[minIndex-1]:tree[minIndex];  
		                   minIndex = (minIndex-1)/2;  
		               }else{                   //如果是左节点  
		                    tree[minIndex/2] = ((Comparable)tree[minIndex]).compareTo(tree[minIndex+1])  
		                        < 0 ? tree[minIndex]:tree[minIndex+1];  
		                    minIndex = minIndex/2;  
		               }  
		           }  
		             
		       }  
		    }  
		//7选择排序  稳定的O(nlog2n)
		public static void treeSelectSort(int arr[]){
			int length=arr.length;
			int treeSize=2*length-1;
			int []treeArr=new int[treeSize];
			for(int i=0;i<length;i++){
				treeArr[treeSize-length+i]=arr[length-1-i];
			}
			//配置二叉树
			for(int i=treeSize-1;i>0;i-=2){
				treeArr[(i-1)/2]=treeArr[i-1]<treeArr[i]?treeArr[i-1]:treeArr[i];
			}
			//以上是弄成二叉树的方法
			for(int i=0;i<length;i++){
				int min=treeArr[0];
				arr[i]=min;
				//然后重置二叉树
				//然后重置二叉树
				//然后重置二叉树
				int minIndex=treeSize-1;
				//查找位置
				while(treeArr[minIndex]!=min){
					minIndex--;
				}
				//上面这段其实不对查找的位置 这样时间复杂度为n  其实应该是记录位置
				//
				treeArr[minIndex]=Integer.MAX_VALUE;
				while(minIndex>0){
					if(minIndex%2==0){
						//右侧二叉树
						treeArr[(minIndex-1)/2]=treeArr[minIndex]<treeArr[minIndex-1]?treeArr[minIndex]:treeArr[minIndex-1];
						minIndex=(minIndex-1)/2;
					}else{
						treeArr[(minIndex)/2]=treeArr[minIndex]<treeArr[minIndex+1]?treeArr[minIndex]:treeArr[minIndex+1];
						minIndex=(minIndex)/2;
					}
				}
			}
		}
		
		
		
		  //堆排序函数7 选择堆排序 不稳定的 O(nlog2n)
	    public static void HeapSort(int[] arr)
	    {
	        int n = arr.length-1;
	       // 以按层从下到上的第
	        //一个非叶子结点开始
	        for(int i=(n-1)/2;i>=0;i--)
	        {
	            //构造大顶堆，从下往上构造
	            //i为最后一个根节点，n为数组最后一个元素的下标
	            HeapAdjust(arr,i,n);
	        }
	        for(int i=n;i>0;i--)
	        {
	            //把最大的数，也就是顶放到最后
	            //i每次减一，因为要放的位置每次都不是固定的
	            swap(arr,i);
	            //再构造大顶堆
	            HeapAdjust(arr,0,i-1);
	        }
	    }

	    //构造大顶堆函数，parent为父节点，length为数组最后一个元素的下标
	    public static void HeapAdjust(int[] arr,int parent,int lastIndex)
	    {
	        //定义临时变量存储父节点中的数据，防止被覆盖
	       
	        //2*parent+1是其左孩子节点 要比较的孩子
	        for(int i=parent*2+1;i<=lastIndex;i=i*2+1)
	        {
	            //如果左孩子大于右孩子，就让i指向右孩子 首先判断有没有右节点
	            if(i<lastIndex && arr[i]<arr[i+1])
	            {
	                i++;
	            }
	            //如果父节点大于或者等于较大的孩子，那就退出循环
	            if(arr[parent]>=arr[i])
	            {
	                break;
	            }
	            //如果父节点小于孩子节点，那就把孩子节点放到父节点上
	            int currentTemp=arr[parent];
	            arr[parent]=arr[i];
	            arr[i]=currentTemp;
//	            arr[parent] = arr[i];
	            //把孩子节点的下标赋值给parent
	            //让其继续循环以保证大根堆构造正确
	            parent = i;
	        }
	        //将刚刚的父节点中的数据赋值给新位置
	        //arr[parent] = temp;
	    }

	    //定义swap函数
	    //功能：将跟元素与最后位置的元素交换
	    //注意这里的最后是相对最后，是在变化的
	    public static void swap(int[] arr,int i)
	    {
	        int temp = arr[0];
	        arr[0] = arr[i];
	        arr[i] = temp;
	    }
	    
	    //8归并排序 稳定的  O(nlog2n)
	    //https://www.cnblogs.com/of-fanruice/p/7678801.html
	    public static void guibingSort(int []arr,int low,int high){
	    	if(arr==null||arr.length<=1){
	    		return;
	    	}
	    	int midle=(low+high)/2;
	    	if(low<high){
	    		guibingSort(arr,low,midle);
	    		guibingSort(arr,midle+1,high);
	    		merge(arr, low, high, midle);
	    	}
	    }
	    //合并
	    public static void merge(int []arr,int low,int high,int mid){
	    	int []tempArr=new int[high-low+1];
	    	int i=low;
	    	int j=mid+1;
	    	int tempIndex=0;
	    	//两边的数组都没到 边界才进行比较
	    	while(i<=mid&&j<=high){
	    		if(arr[i]<arr[j]){
	    			tempArr[tempIndex]=arr[i];
	    			tempIndex++;
	    			i++;
	    		}else{
	    			tempArr[tempIndex]=arr[j];
	    			tempIndex++;
	    			j++;
	    		}
	    	}
	    	//两边的数组另一边到了
	    	while(i<=mid){
	    		tempArr[tempIndex]=arr[i];
    			tempIndex++;
    			i++;
	    		
	    	}
	    	while(j<=high){
	    		tempArr[tempIndex]=arr[j];
    			tempIndex++;
    			j++;
	    	}
	    	for(int x=0;x<tempArr.length;x++){
	    		arr[low+x]=tempArr[x];
	    	}
	    }
	    
	    //java  8种排序算法 https://blog.csdn.net/jackesy/article/details/80135033
//	    	稳定性：就是能保证排序前两个相等的数据其在序列中的先后位置顺序与排序后它们两个先后位置顺序相同。再简单具体一点，如果A i == A j，Ai 原来在 Aj 位置前，排序后 Ai  仍然是在 Aj 位置前。
//
//	    		不稳定：简单选择排序、快速排序、希尔排序、堆排序不是稳定的排序算法
//
//	    		稳定：冒泡排序、直接插入排序、折半插入排序，归并排序和基数排序都是稳定的排序算法。
//
//	    		平均时间复杂度
//
//	    		O(n^2):直接插入排序，简单选择排序，冒泡排序。
//
//	    		在数据规模较小时（9W内），直接插入排序，简单选择排序差不多。当数据较大时，冒泡排序算法的时间代价最高。性能为O(n^2)的算法基本上是相邻元素进行比较，基           本上都是稳定的。
//
//	    		O(nlogn):快速排序，归并排序，希尔排序，堆排序。
//
//	    		其中，快排是最好的， 其次是归并和希尔，堆排序在数据量很大时效果明显。
	    
	    private  static void jishuSort(int []arr){
	    	int length=arr.length;
	    	int tempArr[][]=new int [10][length+1];//二维数组的第一个里面保存的是数量
	    	for(int i=0;i<tempArr.length;i++){
	    		tempArr[i][0]=0;//行中的第一列保存这个行有多少个数据默认为0
	    	}
	    	int maxNumber=getMaxNum(arr);
	    	int maxPosition=getMaxWeishu(maxNumber);
	    	for(int pos=1;pos<=maxPosition;pos++){
	    		for(int i=0;i<length;i++){
	    			int row=getPostion(arr[i], pos);
	    			//这个行里面要加一个数据
	    			//当前的行数第一列中保存的当前有多少数据
	    			tempArr[row][0]=tempArr[row][0]+1;
	    			int clu=tempArr[row][0];
	    			tempArr[row][clu]=arr[i];
	    			
	    		}
	    		int index=0;
	    		for(int i=0;i<tempArr.length;i++){
	    			//从1开始 0为数量
	    			for(int j=1;j<tempArr[i].length;j++){
	    				arr[index]=tempArr[i][j];
	    			}
	        		tempArr[i][0]=0;//行中的第一列保存这个行有多少个数据默认为0
	        	}
	    	}
	    }
	    private static int getMaxNum(int []arr){
	    	int maxNumber=arr[0];
	    	int length=arr.length;
	    	for(int i=1;i<length;i++){
	    		if(arr[i]>maxNumber){
	    			maxNumber=arr[i];
	    		}
	    	}
	    	return maxNumber;
	    }
	    private static int getPostion(int number,int pos){
	    	int temp=1;
	    	for(int i=0;i<pos-1;i++){
	    		temp=temp*10;
	    	}
	    	return (number/temp)%10;
	    }
	    private static int getMaxWeishu(int maxNumber){
	    	return new String(maxNumber+"").length();
	    }
}
