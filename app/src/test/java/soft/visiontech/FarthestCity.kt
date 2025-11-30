package soft.visiontech

class FarthestCity {

    fun findFarthestCity(
        adjacencyList: List<List<Int>>,
        stationCities: List<Int>
    ): Int {
        val cityCount = adjacencyList.size
        val distanceToNearestStation = IntArray(cityCount) { Int.MAX_VALUE }
        val queue = ArrayDeque<Int>()

        // 1) Initialize all station cities with distance 0
        for (station in stationCities) {
            distanceToNearestStation[station] = 0
            queue.addLast(station)
        }

        // 2) Multi-source BFS
        while (queue.isNotEmpty()) {
            val currentCity = queue.removeFirst()
            val currentDistance = distanceToNearestStation[currentCity]

            for (neighbor in adjacencyList[currentCity]) {
                if (distanceToNearestStation[neighbor] > currentDistance + 1) {
                    distanceToNearestStation[neighbor] = currentDistance + 1
                    queue.addLast(neighbor)
                }
            }
        }

        // 3) Farthest city from any station
        var farthestCity = 0
        var maxDistance = -1

        for (city in 0 until cityCount) {
            val dist = distanceToNearestStation[city]
            if (dist != Int.MAX_VALUE && dist > maxDistance) {
                maxDistance = dist
                farthestCity = city
            }
        }

        return farthestCity
    }

}