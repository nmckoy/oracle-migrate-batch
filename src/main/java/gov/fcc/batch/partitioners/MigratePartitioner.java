package gov.fcc.batch.partitioners;


import gov.fcc.run.MigrateInterface;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class MigratePartitioner implements Partitioner{

    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Partitioning database based on count query from MigrateInterface
     * calculating rows to be worked on based on gridSize (number of threads)
     *
     * @param gridSize
     * @return
     */
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

        int min        = 1;
        int max        = jdbcTemplate.queryForInt(MigrateInterface.sqlSelectSomeGetCount);
        int targetSize = (max - min)/gridSize + 1;
        int number     = 0;
        int start      = min;
        int end        = start + targetSize -1;

        while (start <= max) {
            ExecutionContext value = new ExecutionContext();
            result.put("parition" + number, value);

            if (end >= max){
                end = max;
            }

            System.out.println("Thread" + number + " started");
            System.out.println("processing rows " + start + " to " + end);

            value.putString("threadName", "Thread" + number);
            value.putInt("minVal", start);
            value.putInt("maxVal", end);

            start += targetSize;
            end   += targetSize;

            number++;
        }

        return result;
    }


}
