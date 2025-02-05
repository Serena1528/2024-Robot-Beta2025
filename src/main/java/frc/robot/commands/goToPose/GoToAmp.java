// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.goToPose;

import static frc.robot.settings.Constants.DriveConstants.DEFAUL_PATH_CONSTRAINTS;

import java.io.IOException;
import java.util.function.BooleanSupplier;

import org.json.simple.parser.ParseException;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.FileVersionException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class GoToAmp extends Command {
  Command actualCommand;
  /** Creates a new GoToAmp. */
  BooleanSupplier isAllianceRed;
  DrivetrainSubsystem driveTrain;
  public GoToAmp(DrivetrainSubsystem drivetrain) {
    this.driveTrain = drivetrain;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // PathPlannerPath ampPath = PathPlannerPath.fromPathFile("goToAmp");
    try{
    PathPlannerPath ampPath = PathPlannerPath.fromPathFile("ScoreAmp");
    actualCommand = AutoBuilder.pathfindThenFollowPath(ampPath, DEFAUL_PATH_CONSTRAINTS);
    actualCommand.initialize();
    }
  catch(IOException e){System.out.println("got ioException trying to load paths");}
  catch(ParseException i){System.out.println("got ParseException trying to load paths");}
  catch(FileVersionException o){System.out.println("got FileVerionException trying to load paths");}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    actualCommand.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return actualCommand.isFinished();
  }
}
