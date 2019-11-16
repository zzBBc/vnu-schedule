package main;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException {

		json.DriverToSubject.parseDataToJson();

		System.out.println("Completed parse data to json file!");
		System.out.println();

		schedule.ScheduleExcel.returnExcelSchedule();

		System.out.println("Completed created schedule xlsx file!");
		System.out.println();
		System.out.println("Let's start optimized it");

		optimizeSchedule.OptimizeSchedule.returnOptimizedSchedule();

		System.out.println("Everything is done!");
	}

}
