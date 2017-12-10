import java.io.File

fun part1(lines: List<String>) =
        lines.flatMap { Regex("[a-z]+").findAll(it).toList() .map { it.value } }
                .groupingBy { it }.eachCount()
                .filter { it.value == 1 }

val input = File("day_07_input").readLines()
println("solution 1: " + part1(input))

// part 2

data class Node(val name: String, val weight: Int, val edges: MutableList<Node>) {

    fun sumChildWeights(): Int {
        if (edges.isEmpty()) return weight
        if (edges.map { it.sumChildWeights() }.toSet().size != 1) {
            val sortedNodes = edges.sortedBy { it.sumChildWeights() }
            val delta = sortedNodes.last().sumChildWeights() - sortedNodes.first().sumChildWeights()
            throw IllegalStateException("${sortedNodes.last().name} should be ${sortedNodes.last().weight-delta}")
        }
        return weight + edges.sumBy { it.sumChildWeights() }
    }
}

// create nodes in node map
val nodes = mutableMapOf<String, Node>()
for (x in input) {
    val nodeValues = Regex("(\\w+) \\((\\d+)\\)").find(x)!!.groupValues.drop(1)
    val n = Node(nodeValues[0], nodeValues[1].toInt(), mutableListOf())
    nodes[n.name] = n
}

// add node edges
for (x in input) {
    val z = Regex("([a-z]+)").findAll(x).map { it.value }.toList()
    if (z.size > 1) {
        for (i in 1..z.lastIndex) {
            nodes[z[0]]?.edges?.add(nodes[z[i]]!!)
        }
    }
}

nodes["fbgguv"]?.sumChildWeights()
