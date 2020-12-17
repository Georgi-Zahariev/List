public class List {
	// Свойства "първи" и "последен" в редицата
	private Node first, last;

	public List() { // Празен контсруктор
		first = last = null;
	}

	public List(int v) { // Конструктор с един елемент
		first = last = new Node(v);
	}

	// Копи-конструктор: създава НОВ обект като копие на вече съществуващ
	public List(List L) {
		first = last = null;
		Node t = L.first;
		while (t != null) {
			push_back(t.getData());
			t = t.getNext();
		}
	}

	// Гетъри
	public Node getFirst() {
		return first;
	}

	public Node getLast() {
		return last;
	}

	public int getFront() throws NullPointerException {
		// От празения списък не може да се връща "първи елемент"
		if (first == null)
			throw new NullPointerException();
		return first.getData();
	}

	public int getBack() throws NullPointerException {
		// От празения списък не може да се връща "последен елемент"
		if (last == null)
			throw new NullPointerException();
		return last.getData();
	}

	// Достъп до елемент с номер n: линейна сложност
	public int getData(int n) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException();
		Node t = first;
		do {
			if (t == null)
				throw new IllegalArgumentException();
			if (n == 0)
				return t.getData();
			n--;
			t = t.getNext();
		} while (true);
	}

	// Достъп до възел по номер n: линейна сложност
	public Node getNode(int n) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException();
		Node t = first;
		do {
			if (t == null)
				throw new IllegalArgumentException();
			if (n == 0)
				return t;
			n--;
			t = t.getNext();
		} while (true);
	}

	// Сетъри
	public void setFirst(Node n) {
		first = n;
	}

	public void setLast(Node n) {
		last = n;
	}

	public void setFront(int v) throws NullPointerException {
		// Празният спистък няма първи елемент, чиято стойност да се сменя
		if (first == null)
			throw new NullPointerException();
		first.setData(v);
	}

	public void setBack(int v) throws NullPointerException {
		// Празният спистък няма последен елемент, чиято стойност да се сменя
		if (first == null)
			throw new NullPointerException();
		last.setData(v);
	}

	// Промяна на елемент с номер n: линейна сложност
	public void setData(int n, int v) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException();
		Node t = first;
		do {
			if (t == null)
				throw new IllegalArgumentException();
			if (n == 0) {
				t.setData(v);
				return;
			}
			n--;
		} while (true);
	}

	// Други методи
	// Проверка за празен списък
	public boolean isEmpty() {
		return first == null;
	}

	// Вмъкване на първи елемент: константна сложност
	public void push_front(int v) {
		// Ако списъкът е празен, просто създаваме списък с един елемент
		if (first == null) {
			first = last = new Node(v);
			return;
		}
		// Създаване на новия елемент с наследник сегашния
		// първи и без предходник
		Node t = new Node(v, null, first);
		// Предишният първи да има за предходник не null,
		// а новосъздадения
		first.setPrev(t);
		// Новият става вече първи в списъка
		first = t;
	}

	// Вмъкване на последен елемент: константна сложност
	public void push_back(int v) {
		// Ако списъкът е празен, просто създаваме списък с един елемент
		if (first == null)
			first = last = new Node(v);
		else {
			// Създаване на новия елемент с предходник сегашния
			// последен и без наследник
			Node t = new Node(v, last, null);
			// Сегашният последен да има за наследник не null,
			// а новосъздадения
			last.setNext(t);
			// Новосъздаденият става последен в списъка
			last = t;
		}
	}

	// Премахване на първия елемент: константна сложност
	public void pop_front() throws NullPointerException {
		if (first == null)
			throw new NullPointerException();// Няма елементи
		first = first.getNext();// Новият първи е наследникът на досегашния първи
		if (first == null)
			last = null;// Ако вече няма първи, няма и последен
		else
			first.setPrev(null);// Първият няма предходник
	}

	// Премахване на последния елемент: константна сложност
	public void pop_back() throws NullPointerException {
		if (last == null)
			throw new NullPointerException();// Няма елементи
		last = last.getPrev();// Новият последен е предходникът на сегашния последен
		if (last == null)
			first = null;// Ако вече няма последен, няма и първи
		else
			last.setNext(null);// Последният няма наследник
	}

	// Премахване на елемент по указател: константан сложност
	public void remove(Node n) throws NullPointerException {
		if (n == null || first == null)
			throw new NullPointerException();
		if (n == first) {
			pop_front();
			return;
		}
		if (n == last) {
			pop_back();
			return;
		}
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());
	}

	// Премахване на елемент по номер: линейна сложност
	public void remove(int n) throws IllegalArgumentException {
		Node t = first;
		if (t == null || n < 0)
			throw new IllegalArgumentException();
		do {
			if (n == 0) {
				remove(t);
				return;
			}
			n--;
			t = t.getNext();
		} while (t != null);
		throw new IllegalArgumentException();
	}

	// Присъствие на елемент в списъка: линейна сложност
	public boolean contains(int v) {
		boolean b = false;
		Node p = first;
		while (p != null) {
			if (p.getData() == v) {
				b = true;
				break;
			}
			p = p.getNext();
		}
		return b;
	}

	// Размяна на стойностите на елементи: константна сложност
	public void swapValues(Node a, Node b) throws IllegalArgumentException {
		if (a == null || b == null)
			throw new IllegalArgumentException();
		int t = a.getData();
		a.setData(b.getData());
		b.setData(t);
	}

	// Размяна на местата на елементи чрез указателите: константна сложност
	public void swap(Node a, Node b) throws IllegalArgumentException {
		if (a == null || b == null)
			throw new IllegalArgumentException();
		// Един и същ елемент
		if (a == b)
			return;
		Node t;
		// Добавяме елементи в началото и края, за да избегнем проверките за null
		push_front(0);
		push_back(0);
		// Съседни елементи
		if (a.getNext() == b || b.getNext() == a) {
			// Ако са наредени обратно - разменяме ги
			if (b.getNext() == a) {
				t = a;
				a = b;
				b = t;
			}
			Node p1 = a.getPrev();
			Node p2 = b.getNext();
			p1.setNext(b);
			p2.setPrev(a);
			a.setPrev(b);
			b.setNext(a);
			a.setNext(p2);
			b.setPrev(p1);
			// Премахваме допълнителните елементи
			pop_back();
			pop_front();
			return;
		}
		// Елементи в общия случай
		// Обработка на съседите
		a.getPrev().setNext(b);
		a.getNext().setPrev(b);
		b.getNext().setPrev(a);
		b.getPrev().setNext(a);
		// Размяна на указателите
		t = a.getPrev();
		a.setPrev(b.getPrev());
		b.setPrev(t);
		t = a.getNext();
		a.setNext(b.getNext());
		b.setNext(t);
		// Премахвам допълнителните елементи
		pop_back();
		pop_front();
	}

	// Сортиране в нарастващ ред (quickSort): сложност n.log(n)
	// Инструментални методи
	// Нагласяне на ключов елемент
	private Node findKey(Node begin, Node end) {
		boolean f = true;
		while (begin != end) {
			if (begin.getData() > end.getData()) {
				swapValues(begin, end);
				f = !f;
			}
			if (f)
				begin = begin.getNext();
			else
				end = end.getPrev();
		}
		return begin;
	}

	// Бързо сортиране - рекурсивна част
	private void qsort(Node p, Node q) {
		if (p == q)
			return;
		Node t = findKey(p, q);
		if (t != p)
			qsort(p, t.getPrev());
		if (t != q)
			qsort(t.getNext(), q);
	}

	public void quickSort() { // Метод, достъпен за ползвателя
		qsort(first, last);
	}

	// Извеждане във вид на елементи, отделени със запетая
	// Целият списък е заграден с големи скоби
	@Override
	public String toString() {
		String s = "";
		// "Обхождане" на списъка от първия към последния елемент
		Node t = first;
		while (t != null) {
			if (!s.equals(""))
				s += ", ";// Само преди първия елемент няма запетая
			s += t.getData();// "Долепяме" стойността на текущия елемент
			t = t.getNext();// t посочва следващия елемент в списъка
		}
		s = "{" + s + "}";
		return s;
	}

	// Извеждане като елементи (удобно за дебъгинг)
	public String toStringWatch() {
		if (first == null)
			return "null";
		Node t = first;
		String s = "|";
		do {
			s += t.toString() + "|";
			t = t.getNext();
		} while (t != null);
		return s;
	}
}
