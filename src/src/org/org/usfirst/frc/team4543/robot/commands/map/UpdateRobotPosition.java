package src.org.org.usfirst.frc.team4543.robot.commands.map;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.command.Command;

public class UpdateRobotPosition extends Command {
	private double x, y;

	public UpdateRobotPosition(double x, double y) {
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		FieldMap f = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		f.setRobotPosition(x, y);
	}

}
