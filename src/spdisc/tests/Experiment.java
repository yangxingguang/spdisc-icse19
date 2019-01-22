package spdisc.tests;

import java.io.IOException;
import java.io.Writer;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class Experiment {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer;

	public Experiment() {

	}

	public static void main(String[] args) throws IOException {

		
		//** EvaluatePrequentialWFL - the evaluation method. In this case, the evaluator considers the verification latency (i.e. the delay for obtaining the true commit labels)
		//** -l (spdisc.meta.WFL_OO_ORB_Oza -i 15 -s 20 -t 0.99) - the ORB learner where:
		// (1) "-i 15" is the index of the commit timestamp
		// (2) "-s 20" is the ensemble size
		// (3) "-t 0.99" The time decay factor for updating the class size
		//** -s  (ArffFileStream -f (datasets/neutron.arff) -c 15) - indicates the dataset where the class label is in index 15
		//** -e (FadingFactorEachClassPerformanceEvaluator -a 0.99) - indicates the performance evaluator. In this case 
		// the fading factor evaluator with fading factor value 0.99
		//** -d results/neutron(i15s20t0.99)-1.csv - indicates the output file
		String task = "EvaluatePrequentialWFL -l (spdisc.meta.WFL_OO_ORB_Oza -i 15 -s 20 -t 0.99)  -s  (ArffFileStream -f (datasets/neutron.arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a 0.99) -f 1 -d results/neutron(i15s20t0.99)-1.csv";

		try {

			System.out.println(task);
			currentTask = (MainTask) ClassOption.cliStringToObject(task, MainTask.class, null);

			TaskThread thread = new TaskThread((moa.tasks.Task) currentTask);

			thread.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
