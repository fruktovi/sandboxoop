package ru.ssau.tk.fruktovi.sandboxoop.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable,Removable {
    private int count = 0;
    private Node head;

    @Override
    protected int floorIndexOfX(double x) {
        if (x <= head.x) {
            return 0;
        }
        if (x >= head.prev.x) {
            return count - 1;
        }
        Node current = head;
        int index = 0;
        while (current != head.prev && current.x <= x) {
            current = current.next;
            ++index;
        }
        return index - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i<count; i++){
            if (current.x == x){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i<count; i++){
            if (current.y == y){
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public void insert(double x, double y) {
        if (head == null) {
            addNode(x, y);
            return;
        }
        Node cur = head;
        for (int i = 0; i < count; ++i){

            if (cur.x == x){
                cur.y = y;
                return;
            }

            if (cur.x > x){
                Node newNode = new Node(x,y);
                if (cur == head){
                    newNode.next = head;
                    newNode.prev = head.prev;
                    head.prev.next = newNode;
                    head.prev = newNode;
                    head = newNode;
                }
                else {
                    newNode.prev = cur.prev;
                    newNode.next = cur;
                    cur.prev.next = newNode;
                    cur.prev = newNode;
                }
                ++count;
                return;
            }
            cur = cur.next;
        }
        addNode(x, y);
        ++count;

    }

    @Override
    public void remove(int index) {
        if (count != 0){
            Node obj = getNode(index);
            if (index == 0) head = head.next;
            obj.prev.next = obj.next;
            obj.next.prev = obj.prev;
            --count;
        }
    }

    public Node getNode(int index) {
        Node current = head;
        if (index < count / 2) {
            // Если индекс ближе к началу списка
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // Если индекс ближе к концу списка
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    static final class Node {
        private Node next;
        private Node prev;
        private double x;
        private double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "("+ x+ "; " + y+ ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return Double.compare(node.x, x) == 0 && Double.compare(node.y, y) == 0;
        }

        @Override
        public int hashCode() {
            int result = 17;
            long xBits = Double.doubleToLongBits(x);
            long yBits = Double.doubleToLongBits(y);
            result = 31 * result + (int) (xBits ^ (xBits >>> 32));
            result = 31 * result + (int) (yBits ^ (yBits >>> 32));
            return result;
        }

        @Override
        public Object clone() {
            return new Node(this.x, this.y);
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double swap;
            swap = xFrom;
            xFrom = xTo;
            xTo = swap;
        }
        if (xFrom == xTo) {
            for (int i = 0; i < count; ++i) {
                addNode(xFrom, source.apply(xFrom));
            }
        } else {
            addNode(xFrom, source.apply(xFrom));
            double step = (xTo - xFrom) / (count - 1);
            double temp = step;
            for (int i = 1; i < count - 1; ++i) {
                addNode(temp, source.apply(temp));
                temp += step;
            }
            addNode(xTo, source.apply(xTo));
        }

    }

    protected void addNode(double x, double y) {
        Node p = new Node(x, y);
        if (head != null) {
            Node last = head.prev;
            p.prev = last;
            p.next = head;
            last.next = p;
            head.prev = p;
        } else {
            head = p;
            head.next = head;
            head.prev = head;
        }
        count++;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        for (int i = 0; i < count; i++) {
            result.append(current.toString());
            if (i < count - 1) {
                result.append("; "); // Добавляем "; " между точками
            }
            current = current.next;
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListTabulatedFunction that = (LinkedListTabulatedFunction) o;
        if (this.count != that.count) return false;
        Node thisNode = this.head;
        Node thatNode = that.head;
        for (int i = 0; i < count; i++) {
            if (!thisNode.equals(thatNode)) return false;
            thisNode = thisNode.next;
            thatNode = thatNode.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        Node current = head;
        for (int i = 0; i < count; i++) {
            hash = 31 * hash + current.hashCode();
            current = current.next;
        }
        return hash;
    }

}
