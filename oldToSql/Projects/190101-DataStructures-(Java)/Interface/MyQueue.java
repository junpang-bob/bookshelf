package Interface;

public interface MyQueue<E> {
	/*ͨ��������ʵ�ֶ���
	 * 1.����ֻ��ͷβ��˵��
	 * */
	
	//-------------------------------------------����β
	public void offer(E e);
	//-------------------------------------------ɾ��ͷ
	public void poll();
	public void remove();
	//-------------------------------------------�޸�
	//-------------------------------------------��ѯ
	public E peek();
}