import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     void getAfterAddFirstTest(){
         Deque61B<Integer> ad1 = new ArrayDeque61B<>();
         ad1.addFirst(1);// [1]
         ad1.addFirst(0);// [0, 1]
         ad1.addFirst(-1);//[-1, 0, 1]

         int expected = 0;
         int WWG = ad1.get(1);//should be 0
         assertThat(WWG).isEqualTo(expected);
     }

    @Test
    void getAfterAddLastTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);// [1]
        ad1.addLast(2);// [1, 2]
        ad1.addLast(3);// [1, 2, 3]

        int expected = 2;
        int WWG = ad1.get(1);//should be 2
        assertThat(WWG).isEqualTo(expected);
    }

    @Test
    void getAfterAddFirstAndLastTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);// [1]
        ad1.addLast(2);// [1, 2]
        ad1.addFirst(0);// [0, 1, 2]
        ad1.addLast(3);// [0, 1, 2, 3]

        int expected = 1;
        int WWG = ad1.get(1);//should be 1
        assertThat(WWG).isEqualTo(expected);

        //illegal indices (too large/negative):
        assertThat(ad1.get(-1)).isNull();//negative
        assertThat(ad1.get(ad1.size())).isNull();//"too" large
    }

    @Test
    void isEmptyAndSizeAfterAddTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);// [1]
        ad1.addLast(2);// [1, 2]
        ad1.addFirst(0);// [0, 1, 2]
        ad1.addLast(3);// [0, 1, 2, 3]

        int expected = 4;
        int WWG = ad1.size();
        assertThat(WWG).isEqualTo(expected);
        assertThat(ad1.isEmpty()).isFalse();

        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        assertThat(ad2.size()).isEqualTo(0);
        assertThat(ad2.isEmpty()).isTrue();
    }

    @Test
    void isEmptyAndSizeAfterRemoveFirstTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);// [1]
        ad1.addLast(2);// [1, 2]
        ad1.addFirst(0);// [0, 1, 2]
        ad1.addLast(3);// [0, 1, 2, 3]
        ad1.removeFirst();// [1, 2, 3]

        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.isEmpty()).isFalse();

        ad1.removeFirst();//[2, 3]
        ad1.removeFirst();//[3]
        ad1.removeFirst();//[]
        assertThat(ad1.size()).isEqualTo(0);
        assertThat(ad1.isEmpty()).isTrue();
    }

    @Test
    void isEmptyAndSizeAfterRemoveLastTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);// [1]
        ad1.addLast(2);// [1, 2]
        ad1.addFirst(0);// [0, 1, 2]
        ad1.addLast(3);// [0, 1, 2, 3]
        ad1.removeLast();// [0, 1, 2]

        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.isEmpty()).isFalse();

        ad1.removeLast();//[0, 1]
        ad1.removeLast();//[0]
        ad1.removeLast();//[]
        assertThat(ad1.size()).isEqualTo(0);
        assertThat(ad1.isEmpty()).isTrue();
    }

    @Test
    void toListAfterAddFirstLastTest(){
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addFirst("Mid1"); //["Mid1"]
        ad1.addLast("Mid2");
        ad1.addFirst("Front");
        ad1.addLast("Back");

        assertThat(ad1.toList()).containsExactly("Front", "Mid1", "Mid2", "Back").inOrder();
    }

    @Test
    void toListAfterAddAndRemoveTest(){
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addFirst("Mid1"); //["Mid1"]
        ad1.addLast("Mid2");
        ad1.addFirst("Front");
        ad1.addLast("Back");

        ad1.removeFirst();
        ad1.removeLast();//["Mid1", "Mid2"]
        assertThat(ad1.toList()).containsExactly("Mid1", "Mid2").inOrder();

        ad1.removeLast();//["Mid1"]
        assertThat(ad1.toList()).containsExactly("Mid1").inOrder();
    }

    @Test
    void addFirstAndLastResizingUpTest1(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        for (int i = 0; i <= 8;i++){
            if(i % 2 == 0){
                ad1.addFirst(i);//8...64201357, 8 would overwrite item at nextFirst.
            }
            else{
                ad1.addLast(i);
            }
        }
        assertThat(ad1.toList()).containsExactly(8, 6, 4, 2, 0, 1, 3, 5, 7).inOrder();
    }

    @Test
    void addFirstAndLastResizingUpTest2(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        for (int i = 0; i <= 8;i++){
            if(i % 2 == 1){
                ad1.addFirst(i);//753102468
            }
            else{
                ad1.addLast(i);
            }
        }
        assertThat(ad1.toList()).containsExactly(7, 5, 3, 1, 0, 2, 4, 6, 8).inOrder();
    }

    @Test
    void ResizingUpManyTimesTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        List<Integer> expected = new ArrayList<>();

        for (int i = 0; i < 100;i++){
            ad1.addLast(i);
            expected.add(i);
        }
        assertThat(ad1.toList()).containsExactlyElementsIn(expected).inOrder();
    }

    @Test
    void ResizingDownWhileRemoveFirstTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        List<Integer> expected = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            ad1.addLast(i);
            expected.add(i);
        }
        assertThat(ad1.toList()).containsExactlyElementsIn(expected).inOrder();

        for (int j = 0; j <= 75; j++){
            ad1.removeFirst();
            expected.removeFirst();
        }

        assertThat(ad1.toList()).containsExactlyElementsIn(expected).inOrder();
    }

    @Test
    void ResizingDownWhileRemoveLastTest(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        List<Integer> expected = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            ad1.addLast(i);
            expected.add(i);
        }
        assertThat(ad1.toList()).containsExactlyElementsIn(expected).inOrder();

        for (int j = 0; j <= 75; j++){
            ad1.removeLast();
            expected.removeLast();
        }

        assertThat(ad1.toList()).containsExactlyElementsIn(expected).inOrder();
    }
}
