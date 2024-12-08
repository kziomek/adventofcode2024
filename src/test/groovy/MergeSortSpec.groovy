import advent.day1.MergeSort
import spock.lang.Specification


class MergeSortSpec extends Specification {

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

    def "sort "() {
        given:
        int[] arr = [3, 6, 5]
        when:
        new MergeSort().sort(arr)
        then:
        arr == [3, 5, 6] as int[]


    }

}