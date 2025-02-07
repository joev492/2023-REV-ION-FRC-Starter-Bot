// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.lib.PIDGains;
import java.lang.Math;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OIConstants {
        public static final int kDriverController = 0;
        public static final int kAssistantController = 1;
        public static final double kDriveDeadband = 0.05;
        public static final double kArmManualDeadband = 0.05;
        public static final double kArmManualScale = 0.5;
    }
    
    public static final class Drivetrain {
        public static final int kFrontLeftPWMId = 0;
        public static final int kFrontRightPWMId = 1;
        public static final int kRearLeftPWMId = 2;
        public static final int kRearRightPWMId = 3;

        public static final boolean kFrontLeftInverted = true;
        public static final boolean kFrontRightInverted = false;
        public static final boolean kRearLeftInverted = true;
        public static final boolean kRearRightInverted = false;

        public static final int kCurrentLimit = 40;

        public static final double kTurningScale = 0.5;
    }

    public static final class Brake {
        public static final int kPneumHubCanID = 2;
        
        public static final int kDoubleSolenoid1Extend = 1;
        public static final int kDoubleSolenoid1Retract = 2;

        public static final int kDoubleSolenoid2Extend = 3;
        public static final int kDoubleSolenoid2Retract = 4;
    }

    public static final class Arm {
        public static final int kArmCanId = 3;
        public static final boolean kArmInverted = false;
        public static final int kCurrentLimit = 50;

        public static final double kSoftLimitReverse = 0.0;
        public static final double kSoftLimitForward = 4.6;

<<<<<<< HEAD
        public static final double kArmGearRatio = 1 / (32 * 4) * 2 * Math.PI; //multiply SM value by this number and get arm position in radians (PREVIOUSLY 48)
=======
        public static final double kArmGearRatio = 1.0 / (48.0 * 4.0); 
        public static final double kPositionFactor = kArmGearRatio * 2.0 * Math.PI; //multiply SM value by this number and get arm position in radians
        public static final double kVelocityFactor = kArmGearRatio * 2.0 * Math.PI / 60.0;
        public static final double kArmFreeSpeed = 5676.0 * kVelocityFactor;
>>>>>>> 528a508a7eb6b53334f405501db7e71b1021b3ef
        public static final double kArmZeroCosineOffset = - Math.PI / 6; //radians to add to converted arm position to get real-world arm position (starts at ~30deg angle)
        public static final ArmFeedforward kArmFeedforward = new ArmFeedforward(0.0, 0.4, 12.0/kArmFreeSpeed, 0.0);
        public static final PIDGains kArmPositionGains = new PIDGains(0.6, 0.0, 0.0);
        public static final TrapezoidProfile.Constraints kArmMotionConstraint = new TrapezoidProfile.Constraints(-2.0, -2.0);

        public static final double kHomePosition = 0.0;
        public static final double kScoringPosition = 2; //was positive 3.05
        public static final double kIntakePosition = 2.98; //was positive 4.52
        public static final double kFeederPosition = 1.95; //was positive 2.95
    } 

    public static final class Gripper {
        public static final int kGripperCanId = 4;
        public static final boolean kGripperInverted = true;
        public static final double kSoftLimitReverse = -34.0; //was negative
        public static final double kSoftLimitForward = 5.0; //was positive
        public static final double kClosePosition = 0.0;
        public static final double kOpenPosition = -34.0; //was negative
        public static final double kSafePosition = -29.0; //was negative
        public static final int kCurrentLimit = 30;
        public static final PIDGains kPositionPIDGains = new PIDGains(0.2, 0.0, 0.0);
    }
}
