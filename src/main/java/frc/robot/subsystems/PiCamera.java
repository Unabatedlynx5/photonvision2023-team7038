package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Debug;
import frc.robot.Constants.subsystemDebuggers;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

public class PiCamera extends SubsystemBase {
    //Debugger
	private final Debug m_debugger = new Debug("subsystem PhotonCamera");

    //Declarations

    //Setup
    PhotonCamera kPiCam = new PhotonCamera("IMX219");

    //Main
    public PiCamera() {
        //Debugger Enable?
        if (subsystemDebuggers.kCameraDebug) {
            m_debugger.enable();
        }
        var result = kPiCam.getLatestResult();
        boolean hasTargets = result.hasTargets();

        //Get list of all targets
        List<PhotonTrackedTarget> targets = result.getTargets();

        //Get best Target
        //Use targetSort to determine best type
        PhotonTrackedTarget target = result.getBestTarget();

    }
    public class TargetHolder {
    
        public double yaw;
        public double pitch; 
        public double area;
        public double skew;
        //Transform2d pose = target.getCameraToTarget();
        public List<TargetCorner> corners;


        public TargetHolder(PhotonTrackedTarget target) {
         yaw = target.getYaw();
         pitch = target.getPitch();
         area = target.getArea();
         skew = target.getSkew();

         corners = target.getDetectedCorners();
        }
    }
    public class AprilTag {
        public int targetID;
        public double poseAmbiguity;
        
    }
}
