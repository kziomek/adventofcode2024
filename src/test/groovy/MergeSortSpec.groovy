import advent.day1.MergeSort
import spock.lang.Specification


class MergeSortSpec extends Specification {

    def "null arr"() {
        given:
        int[] arr = null
        when:
        new MergeSort().sort(arr)
        then:
        arr == null
    }

    def "sort empty"() {
        given:
        int[] arr = []
        when:
        new MergeSort().sort(arr)
        then:
        arr == [] as int[]
    }

    def "sort one element"() {
        given:
        int[] arr = [1]
        when:
        new MergeSort().sort(arr)
        then:
        arr == [1] as int[]
    }

    def "sort two element sorted"() {
        given:
        int[] arr = [1, 2]
        when:
        new MergeSort().sort(arr)
        then:
        arr == [1, 2] as int[]
    }

    def "sort two elements unsorted"() {
        given:
        int[] arr = [2, 1]
        when:
        new MergeSort().sort(arr)
        then:
        arr == [1, 2] as int[]
    }

    def "sort 3 elements"() {
        given:
        int[] arr = [3, 6, 5]
        when:
        new MergeSort().sort(arr)
        then:
        arr == [3, 5, 6] as int[]
    }

    def "sort 6 elements from day 1 input: #arr"() {
        when:
        new MergeSort().sort(arr)
        then:
        arr == sorted
        where:
        arr                         | sorted
        [3, 4, 2, 1, 3, 3] as int[] | [1, 2, 3, 3, 3, 4] as int[]
        [4, 3, 5, 3, 9, 3] as int[] | [3, 3, 3, 4, 5, 9] as int[]
    }

}