package ats.classifiers;

import java.util.ArrayList;
import java.util.Random;

import ats.DataSet;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class DecisionTree {

	private J48 decisionTree = null;

	public DecisionTree() {
		super();
	}

	// Build classifier
	public void buildDecisionTree(Instances trainingData) {

		try {
			String[] options = new String[1];
			options[0] = "-U"; // unpruned tree
			decisionTree = new J48(); // new instance of tree
			decisionTree.setOptions(options);
			decisionTree.buildClassifier(trainingData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Classify test data set
	public void classify(Instances testData) {

		ArrayList<Double> classificationResultSet = new ArrayList<Double>();
		Double classificationResult = null;

		for (int i = 0; i < testData.numInstances(); i++) {

			try {
				classificationResult = decisionTree.classifyInstance(testData
						.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			classificationResultSet.add(i, classificationResult);
		}

		for (int i = 0; i < classificationResultSet.size(); i++) {
			System.out.println(classificationResultSet.get(i));
		}
	}

	// Evaluate classifier through cross validation
	public void crossValidate() {

		DataSet dataSet = new DataSet();
		Instances newData = dataSet.getInstances();

		try {
			Evaluation eval = new Evaluation(newData);
			J48 decisionTree = new J48();
			eval.crossValidateModel(decisionTree, newData, newData
					.numInstances(), new Random(1));
			System.out.println(eval.toSummaryString("\nResults\n\n", false));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}