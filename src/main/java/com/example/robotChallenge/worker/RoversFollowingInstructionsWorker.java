package com.example.robotChallenge.worker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.exception.EmptyFileException;
import com.example.robotChallenge.factory.PlateauFactory;
import com.example.robotChallenge.factory.RoverFactory;
import com.example.robotChallenge.model.Plateau;
import com.example.robotChallenge.model.Rover;

public class RoversFollowingInstructionsWorker implements IRoversFollowingInstructionsWorker{
    private RoverFactory roverFactory;
    private PlateauFactory plateauFactory;
    private static RoversFollowingInstructionsWorker roversFollowingInstructions;

    
    /** 
     * @return RoversFollowingInstructionsWorker
     * Using the design pattern singleton we make sure to have only one instance of 
     * this worker that will simulate the whole sitation
     */
    public static RoversFollowingInstructionsWorker getInstance() {
        if (roversFollowingInstructions == null)
            roversFollowingInstructions = new RoversFollowingInstructionsWorker(new RoverFactory(), new PlateauFactory());
        return roversFollowingInstructions;
    }

    
    /** 
     * Take the path to the file extract the data and dispatch it the simulation function
     * @param path path to the file that contains the day
     * @throws EmptyFileException in case the file has no content
     * @throws BadDataEntryException in case the data that is put does not meet the
     * required type
     */
    public void dispatchData(String path) throws EmptyFileException, BadDataEntryException {
        String content = readFile(path);
        String[] instructions = split(content, "\n");
        String[] plateauCordinates = split(getString(instructions, 0), " ");
        int xPlateau = convertToInt(plateauCordinates, 0);
        int yPlateau = convertToInt(plateauCordinates, 1);
        String[] roversToCreate = new String[instructions.length / 2];
        String[] roversInStructions = new String[instructions.length / 2];
        int l = 0, k = 0;
        for (int i = 1; i < instructions.length; i++) {
            if (i % 2 != 0)
                roversToCreate[l++] = instructions[i];
            else
                roversInStructions[k++] = instructions[i];
        }
        List<Rover> rovers = roversFollowingInstructions.simulateInstructions(xPlateau, yPlateau, roversToCreate,
                roversInStructions);
        rovers.stream().forEach(x -> System.out.println(x));
    }

    private RoversFollowingInstructionsWorker(RoverFactory roverFactory, PlateauFactory plateauFactory) {
        this.roverFactory = roverFactory;
        this.plateauFactory = plateauFactory;
    }

    
    /** 
     * 
     * @param xPlateau x of the plateau
     * @param yPlateau y of the plateau
     * @param roversToCreate array of the strings that specify the positions of the rovers to create
     * @param roversInStructions array of strings that specify the instruction corresponding to each rover
     * @return List<Rover> the rovers after they were redirectionned and moved following the instructions
     * @throws BadDataEntryException
     */
    public List<Rover> simulateInstructions(int xPlateau, int yPlateau, String[] roversToCreate,
            String[] roversInStructions) throws BadDataEntryException {
        Plateau plateau = plateauFactory.createPlateau(xPlateau, yPlateau);
        List<Rover> rovers = new ArrayList<Rover>();
        int k = 0;
        for (String roverToCreate : roversToCreate) {
            String[] creationsInstructions = roverToCreate.split(" ");
            Rover rover = roverFactory.createRover(Integer.parseInt(creationsInstructions[0].trim()),
                    Integer.parseInt(creationsInstructions[1].trim()), creationsInstructions[2]);
            this.followInstructions(plateau, rover, roversInStructions[k++]);
            rovers.add(rover);
        }
        return rovers;
    }

    
    /** 
     * @param plateau the plateau that we will be working on
     * @param rover
     * @param roversInStructionsStr
     * @return Rover
     * @throws BadDataEntryException
     */
    public Rover followInstructions(Plateau plateau, Rover rover, String roversInStructionsStr)
            throws BadDataEntryException {
        String[] roversInStructions = roversInStructionsStr.trim().split("(?!^)");
        for (String roverInstruction : roversInStructions) {
            if (roverInstruction.equals("M")) {
                if (this.roverFactory.validateMovement(rover, plateau)) {
                    rover = this.roverFactory.advance(rover);
                }
            } else {
                rover = this.roverFactory.rotate(rover, roverInstruction);
            }
        }
        return rover;
    }

    
    /** 
     * @param path
     * @return String
     * @throws EmptyFileException
     */
    private String readFile(String path) throws EmptyFileException {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new EmptyFileException();
        }
    }

    
    /** 
     * @param s
     * @param i
     * @return String[]
     * @throws BadDataEntryException
     */
    private String[] split(String s, String i) throws BadDataEntryException {
        if (s.length() > 0)
            return s.trim().split(i);

        throw new BadDataEntryException();
    }

    
    /** 
     * @param str
     * @param i
     * @return String
     * @throws BadDataEntryException
     */
    private String getString(String[] str, int i) throws BadDataEntryException {
        if (str.length >= i + 1)
            return str[i];
        throw new BadDataEntryException();

    }

    
    /** 
     * @param str
     * @param i
     * @return int
     * @throws BadDataEntryException
     */
    private int convertToInt(String[] str, int i) throws BadDataEntryException {
        try {
            return Integer.valueOf(getString(str, i));
        } catch (Exception e) {
            throw new BadDataEntryException();
        }

    }
}
