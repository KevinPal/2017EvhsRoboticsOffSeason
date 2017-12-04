package src.org.org.usfirst.frc.team4543.robot.commands.map;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.command.Command;

public class UpdateTargetPosition extends Command {
	private double x, y;

	public UpdateTargetPosition(double x, double y) {
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		this.x = x;
		this.y = y;
	}

	@Override
	protected void execute() {
		System.out.print((((FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP)).setTargetPosition(x, y, false)) ? ""
				: "Updating targetPosition has failed\n");
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
