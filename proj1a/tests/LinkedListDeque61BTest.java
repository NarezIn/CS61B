import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This test checks if isEmpty() and size() work. */
    public void testSizeAndIsEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        assertThat(lld1.size()).isEqualTo(0);

        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld2.addLast(50);
        assertThat(lld2.isEmpty()).isFalse();
        assertThat(lld2.size()).isEqualTo(1);

        Deque61B<Integer> lld3 = new LinkedListDeque61B<>();
        lld3.addLast(2);
        lld3.addFirst(1);
        lld3.addLast(3);
        assertThat(lld3.isEmpty()).isFalse();
        assertThat(lld3.size()).isEqualTo(3);
    }

    @Test
    /** This test checks if the get() method works, including those edge cases. */
    public void testGet(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(3);
        lld1.addFirst(2);
        lld1.addFirst(1);//1, 2, 3

        assertThat(lld1.get(2)).isEqualTo(3);
        assertThat(lld1.get(1)).isEqualTo(2);
        assertThat(lld1.get(0)).isEqualTo(1);

        assertThat(lld1.get(3)).isNull();
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(123)).isNull();
    }

    @Test
    /** This test checks if the getRecursive() method works, including those edge cases. */
    public void testGetRecursive(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(3);
        lld1.addFirst(2);
        lld1.addFirst(1);//1, 2, 3

        assertThat(lld1.getRecursive(2)).isEqualTo(3);
        assertThat(lld1.getRecursive(1)).isEqualTo(2);
        assertThat(lld1.getRecursive(0)).isEqualTo(1);

        assertThat(lld1.getRecursive(3)).isNull();
        assertThat(lld1.getRecursive(-1)).isNull();
        assertThat(lld1.getRecursive(123)).isNull();
    }

    @Test
    /** This test checks if removeFirst and removeLast works, along with using size()/isEmpty(). */
    public void testRemoveFirstAndRemoveLast() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        lld1.removeFirst(); // [-1, 0, 1, 2]
        assertThat(lld1.toList()).containsExactly(-1, 0, 1, 2).inOrder();
        assertThat(lld1.size()).isEqualTo(4);

        lld1.removeFirst(); // [0, 1, 2]
        assertThat(lld1.toList()).containsExactly(0, 1, 2).inOrder();
        assertThat(lld1.size()).isEqualTo(3);

        lld1.removeLast();  // [0, 1]
        assertThat(lld1.toList()).containsExactly(0, 1).inOrder();
        assertThat(lld1.size()).isEqualTo(2);

        lld1.removeLast();  // [0]
        assertThat(lld1.toList()).containsExactly(0).inOrder();
        assertThat(lld1.size()).isEqualTo(1);

        lld1.removeFirst(); // []
        assertThat(lld1.isEmpty()).isTrue();
        assertThat(lld1.size()).isEqualTo(0);

        assertThat(lld1.removeFirst()).isNull();
    }

    // some extra tests
    @Test
    /** Test of adding some elements to a deque and remove them all,
     * then check that addFirst and addLast still works. */
    public void addFirstAndLastAfterRemoveToEmpty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addFirst(-1);
        assertThat(lld1.toList()).containsExactly(-1, 0, 1).inOrder();

        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly().inOrder();

        lld1.addFirst(100);
        assertThat(lld1.toList()).containsExactly(100).inOrder();

        lld1.removeLast();
        lld1.addLast(200);
        assertThat(lld1.toList()).containsExactly(200).inOrder();
    }

}