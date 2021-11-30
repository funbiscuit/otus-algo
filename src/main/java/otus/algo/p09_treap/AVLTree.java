package otus.algo.p09_treap;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

public class AVLTree<T extends Comparable<T>> {
    Node<T> root = null;

    public void inorder(Consumer<T> consumer) {
        if (root != null) {
            root.inorder(consumer);
        }
    }

    public void insert(T item) {
        if (root == null) {
            root = new Node<>(item);
            return;
        }

        root.insert(new Node<>(item));

        root = Node.rebalance(root);
    }

    public void remove(T item) {
        root = Node.remove(root, item);
    }


    private static int getBalance(Node<?> node) {
        if (node == null)
            return 0;
        return getHeight(node.getRight()) - getHeight(node.getLeft());
    }

    private static int getHeight(Node<?> node) {
        return node == null ? 0 : node.height;
    }

    @Getter
    private static class Node<T extends Comparable<T>> {
        private final T key;
        @Setter
        private Node<T> left;
        @Setter
        private Node<T> right;
        private int height;

        public Node(T key) {
            this.key = key;
        }

        public void updateHeight() {
            height = Math.max(AVLTree.getHeight(right), AVLTree.getHeight(left)) + 1;
        }

        public void inorder(Consumer<T> consumer) {
            if (left != null) {
                left.inorder(consumer);
            }
            consumer.accept(key);
            if (right != null) {
                right.inorder(consumer);
            }
        }

        public void insert(Node<T> newNode) {
            if (newNode.key.compareTo(key) <= 0) {
                if (left == null) {
                    left = newNode;
                } else {
                    left.insert(newNode);
                }
            } else {
                if (right == null) {
                    right = newNode;
                } else {
                    right.insert(newNode);
                }
            }
        }

        public static <K extends Comparable<K>> Node<K> rebalance(Node<K> node) {
            node.updateHeight();

            if (getBalance(node) == 2) {
                if (getBalance(node.right) < 0) {
                    node.right = rotateRight(node.right);
                }
                return rotateLeft(node);
            }
            if (getBalance(node) == -2) {
                if (getBalance(node.left) > 0) {
                    node.left = rotateLeft(node.left);
                }
                return rotateRight(node);
            }

            return node;
        }

        /**
         * Perform right rotation via node
         *
         * @param node node along which rotation is performed (root before rotation)
         * @return tree root after rotation
         */
        private static <K extends Comparable<K>> Node<K> rotateRight(Node<K> node) {
            Node<K> newRoot = node.left;
            node.left = newRoot.right;
            newRoot.right = node;
            node.updateHeight();
            newRoot.updateHeight();
            return newRoot;
        }

        private static <K extends Comparable<K>> Node<K> rotateLeft(Node<K> node) {
            Node<K> newRoot = node.right;
            node.right = newRoot.left;
            newRoot.left = node;
            node.updateHeight();
            newRoot.updateHeight();
            return newRoot;
        }

        public static <K extends Comparable<K>> Node<K> remove(Node<K> root, K key) {
            if (root == null) {
                return null;
            }

            int cmp = key.compareTo(root.key);

            if (cmp < 0) {
                root.left = remove(root.left, key);
            } else if (cmp > 0) {
                root.right = remove(root.right, key);
            } else {
                Node<K> left = root.left;
                Node<K> right = root.right;
                if (right == null) {
                    return left;
                }
                Node<K> min = right.findMin();
                min.right = right.removeMin();
                min.left = left;
                return rebalance(min);
            }
            return rebalance(root);
        }

        public Node<T> findMin() {
            if (left == null) {
                // current node is min, just return its right tree
                return this;
            }
            return left.findMin();
        }

        public Node<T> removeMin() {
            if (left == null) {
                // current node is min, just return its right tree
                return right;
            }
            left = left.removeMin();
            return rebalance(this);
        }
    }
}
