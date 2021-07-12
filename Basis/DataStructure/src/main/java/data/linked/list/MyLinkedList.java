package data.linked.list;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/6/1 09:43
 */
@Slf4j
public class MyLinkedList<E> {

	private transient Node head;
	private transient Node tail;

	public void add(E e) {
		if (head == null) {
			head = new Node(null, e, null);
			tail = head;
		} else {

		}
	}

	@AllArgsConstructor
	class Node {
		private Node prev;
		private E item;
		private Node next;
	}

}
