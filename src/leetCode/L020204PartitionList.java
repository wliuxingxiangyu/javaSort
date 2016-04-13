package leetCode;

public class L020204PartitionList {
	public static ListNode partition(ListNode head, int x) {
		if (head == null || head.next == null)
			return head;
		ListNode cur, pre = head;
		for (cur = head; cur.next != null;) {
			if (cur.val == x) {

				break;
			} else {
				pre = cur;
				cur = cur.next;

			}

		}

		for (ListNode p = cur; p.next != null; p = p.next) {
			System.out.println("p.val ="+p.val );
			if (p.val < x) {
				ListNode q = p.next;
				p.next = cur;
				if (cur != pre) {
					pre.next = p;
					pre = pre.next;
				} else { // cur和pre指向了同一个
					head = p;
				}
				System.out.println("p = q-前-q.val ="+q.val );
				p = q;
				System.out.println("p = q-后-p.val ="+p.val );
			}

		}

		return head;
	}

	public static void main(String[] args) {
		ListNode h=new ListNode(3);
		h.next=new ListNode(3);
		h.next.next=new ListNode(1);
		h.next.next.next=new ListNode(2);
		h.next.next.next.next=new ListNode(4);
		h.next.next.next.next.next=null;
		partition(h, 3);
		System.out.println("shu chu");
	}

}
