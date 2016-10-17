Drop DATABASE GeoDB;
CREATE DATABASE GeoDB;
USE GeoDB;

CREATE TABLE tbl_TermType(
TermID INT AUTO_INCREMENT,
TermName varchar(20) NOT NULL,
DispayOrder INT,
CONSTRAINT pk_TermID PRIMARY KEY(TermID)
);

CREATE TABLE tbl_PhaseType(
PhaseID INT AUTO_INCREMENT,
PhaseName varchar(20) NOT NULL,
PhaseOrder INT,
CONSTRAINT pk_PhaseID PRIMARY KEY(PhaseID));

CREATE TABLE tbl_RockType(
RockTypeID INT AUTO_INCREMENT,
RockTypeName varchar(30) NOT NULL,
RockTypeOrder INT,
CONSTRAINT pk_RockTypeID PRIMARY KEY(RockTypeID)
);

CREATE TABLE tbl_SpecificGravityType(
SpecificGravityID INT AUTO_INCREMENT,
SpecificGravityName varchar(30) NOT NULL,
SpecificGravityOrder INT,
CONSTRAINT pk_SpecificGravityID PRIMARY KEY(SpecificGravityID)
);

CREATE TABLE tbl_GeometryType(
GeometryTypeID INT AUTO_INCREMENT,
GeometrySets INT,
AngleType varchar(20),
GeometryOrder INT,
CONSTRAINT pk_GeometryTypeID PRIMARY KEY(GeometryTypeID)
);

CREATE TABLE tbl_StreakColorType(
StreakColorID INT AUTO_INCREMENT,
StreakColorName varchar(30) NOT NULL,
StreakColorOrder INT,
CONSTRAINT pk_StreakColorID PRIMARY KEY(StreakColorID)
);

CREATE TABLE tbl_HardnessType(
HardnessTypeID SMALLINT(2),
HardnessName varchar(30) NOT NULL,
HardnessOrder SMALLINT(2),
HardnessMin INT,
HardnessMax INT,
CONSTRAINT pk_HardnessTypeID PRIMARY KEY(HardnessTypeID)
);

CREATE TABLE tbl_LusterType(
LusterTypeID INT AUTO_INCREMENT,
LusterName varchar(20),
CONSTRAINT pk_LusterTypeID PRIMARY KEY(LusterTypeID)
);

CREATE TABLE tbl_AttributeType(
AttributeID INT AUTO_INCREMENT,
AttributeName varchar(20),
AttributeOrder INT,
CONSTRAINT pk_AttributeID PRIMARY KEY(AttributeID)
);

-- creating a User TABLE
CREATE TABLE tbl_User (
UserID INT AUTO_INCREMENT,
LoginName varchar(30) NOT NULL UNIQUE,
UserPassword varchar(30) NOT NULL,
Name varchar(50) NOT NULL,
EmailAddress varchar(40) NOT NULL,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_UserID PRIMARY KEY(UserID)
)ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tblUser
BEFORE INSERT ON tbl_User
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblUser
BEFORE UPDATE ON tbl_User
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

-- Trigger to insert GUID for UserID
/*CREATE TRIGGER before_insert_tblUser
  BEFORE INSERT ON tbl_User
  FOR EACH ROW
  SET new.UserID = uuID(); */

 -- Roles of User ID 
CREATE TABLE tbl_RoleType(
RoleTypeID INT,
RoleName varchar(20) NOT NULL,
PRIMARY KEY(RoleTypeID)
);
-- User Roles
CREATE TABLE tbl_UserRole(
UserID INT,
RoleTypeID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
PRIMARY KEY(UserID,RoleTypeID),
CONSTRAINT fk_UserID FOREIGN KEY(UserID) REFERENCES tbl_User(UserID),
CONSTRAINT fk_roleTypeID FOREIGN KEY(RoleTypeID) REFERENCES tbl_RoleType(RoleTypeID)
);

CREATE TRIGGER insert_createdBy_tblUserRole
BEFORE INSERT ON tbl_UserRole
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblUserRole
BEFORE UPDATE ON tbl_UserRole
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_Section
       (SectionID INT AUTO_INCREMENT,
	Title VARCHAR(50),
	Year  MEDIUMINT,
	TermID INT,
	Basicdescription varchar(100),
	ClassURL varchar(500),
	ExpectedNoofStudents MEDIUMINT,
	EnrollmentPassword CHAR(12),
	AllowEnrollement CHAR(2),
	ClassPinsAreVisible ENUM('Enable','Disable') DEFAULT 'Disable',
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
constraint pk_SectionID PRIMARY KEY(SectionID),
constraint fk_TermID FOREIGN KEY(TermID) REFERENCES tbl_TermType(TermID)
)ENGINE=InnoDB AUTO_INCREMENT=200001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tblSection
BEFORE INSERT ON tbl_Section
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblSection
BEFORE UPDATE ON tbl_Section
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

-- Student TABLE

CREATE TABLE tbl_Student(
StudentID INT,
SectionID INT,
IsActive Char(2),
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_studentId PRIMARY KEY(StudentID),
CONSTRAINT fk_StudentID FOREIGN KEY(StudentID) REFERENCES tbl_User(UserID),
CONSTRAINT fk_sectionID FOREIGN KEY(SectionID) REFERENCES tbl_Section(SectionID)
);

CREATE TRIGGER insert_createdBy_tblStudent
BEFORE INSERT ON tbl_Student
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblStudent
BEFORE UPDATE ON tbl_Student
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

-- creating instructor section
CREATE TABLE tbl_InstructorSection(
InstructorID INT,
SectionID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
PRIMARY KEY(InstructorID, SectionID),
CONSTRAINT fk_InstructorID FOREIGN KEY(InstructorID) REFERENCES tbl_User(UserID),
CONSTRAINT fk_Ins_sectionID FOREIGN KEY(SectionID) REFERENCES tbl_Section(SectionID)
);

CREATE TRIGGER insert_createdBy_tblInstructorSection
BEFORE INSERT ON tbl_InstructorSection
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblInstructorSection
BEFORE UPDATE ON tbl_InstructorSection
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_Quiz (
QuizID INT AUTO_INCREMENT, 
QuizName VARCHAR(50), 
QuizDesc VARCHAR(100),
NumQuestions INT, 
Num_Takes_Max INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root', 
CONSTRAINT pk_QuizID PRIMARY KEY(QuizID)
)ENGINE=InnoDB AUTO_INCREMENT=300001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tbl_Quiz
BEFORE INSERT ON tbl_Quiz
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_Quiz
BEFORE UPDATE ON tbl_Quiz
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

-- creating assignment table
CREATE TABLE tbl_Assignment
   (AssignID INT AUTO_INCREMENT,
	QuizID INT,
	SectionID INT,
	Name Varchar(100),
	Due_date date,
	Description varchar(5000),
	Possiblepointsphase1 DECIMAL(5,2),
	Possiblepointsphase2 DECIMAL(5,2),
	Possiblepointsphase3 DECIMAL(5,2),
	IsActive CHAR(2),
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
constraint pk_AssignID PRIMARY KEY(AssignID),
constraint fk_Assign_SectionID FOREIGN KEY(SectionID) REFERENCES tbl_Section(SectionID),
constraint fk_Assign_QuizID FOREIGN KEY(QuizID) REFERENCES tbl_Quiz(QuizID)
)ENGINE=InnoDB AUTO_INCREMENT=400001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tblAssignment
BEFORE INSERT ON tbl_Assignment
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblAssignment
BEFORE UPDATE ON tbl_Assignment
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER(); 

-- creating sumission table
CREATE TABLE tbl_Submission
       (SubmissionID INT AUTO_INCREMENT,
	StudentID INT,
	AssignmentID INT,
	Description varchar(100),
	RockType varchar(100),
	RockName varchar(100),
	CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
constraint pk_SubmissionID PRIMARY KEY(SubmissionID),
constraint fk_sub_StudentID FOREIGN KEY(StudentID) REFERENCES tbl_User(UserID),
constraint fk_AssignmentID FOREIGN KEY(AssignmentID) REFERENCES tbl_Assignment(AssignID)
)ENGINE=InnoDB AUTO_INCREMENT=500001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tblSubmission
BEFORE INSERT ON tbl_Submission
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblSubmission
BEFORE UPDATE ON tbl_Submission
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER(); 

-- creating table mineral submission
CREATE TABLE tbl_MineralSubmission
       (MineralID INT AUTO_INCREMENT,
	SubmissionID INT,
	Luster varchar(50),
	Hardness varchar(50),
	StreakColor varchar(50),
	SpecificGravity varchar(50),
	Geometry varchar(50),
	Color varchar(50),
	Name varchar(50),
	DisplayOrder INT,
	CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
constraint pk_MineralID PRIMARY KEY(MineralID),
constraint fk_min_SubmissionID FOREIGN KEY(SubmissionID) REFERENCES tbl_Submission(SubmissionID)
)ENGINE=InnoDB AUTO_INCREMENT=600001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tblMineralSubmission
BEFORE INSERT ON tbl_MineralSubmission
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblMineralSubmission
BEFORE UPDATE ON tbl_MineralSubmission
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

-- creating attribute evaluation table
CREATE TABLE tbl_AttributeEvaluation
       (MineralID INT,
	AttributeID INT,
	IsAccepted CHAR(2),
	Comments varchar(3000),
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
constraint pk_att_MineralID PRIMARY KEY(MineralID,AttributeID),
constraint fk_att_MineralID FOREIGN KEY(MineralID) REFERENCES tbl_MineralSubmission(MineralID),
constraint fk_att_AttributeID FOREIGN KEY(AttributeID) REFERENCES tbl_AttributeType(AttributeID)
);

CREATE TRIGGER insert_createdBy_tblAttributeEvaluation
BEFORE INSERT ON tbl_AttributeEvaluation
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblAttributeEvaluation
BEFORE UPDATE ON tbl_AttributeEvaluation
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();


-- creating Phase evaluation table
CREATE TABLE tbl_PhaseEvaluation
       (SubmissionID INT,
	Phase INT,
	IsAccepted CHAR(2),
	Comments VARCHAR(300),
	Points DECIMAL(5,2),
	CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
PRIMARY KEY(SubmissionID,Phase),
constraint fk_eval_SubmissionID FOREIGN KEY(SubmissionID) REFERENCES tbl_Submission(SubmissionID),
constraint fk_eval_Phase FOREIGN KEY(Phase) REFERENCES tbl_PhaseType(PhaseID)
);

CREATE TRIGGER insert_createdBy_tblPhaseEvaluation
BEFORE INSERT ON tbl_PhaseEvaluation
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblPhaseEvaluation
BEFORE UPDATE ON tbl_PhaseEvaluation
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_ImageSubmission
    (ImageID INT AUTO_INCREMENT,
	SubmissionID INT,
	ImageLocation varchar(100),
	Latitude  varchar(50),
	Longitude varchar(50),
	ImageOrder INT,
	IsBest CHAR(2),
	Image BLOB DEFAULT NULL,
	IsShow CHAR(1),
	CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
constraint pk_image_ImageID PRIMARY KEY(ImageID),
constraint fk_image_SubmissionID FOREIGN KEY(SubmissionID) REFERENCES tbl_Submission(SubmissionID)
)ENGINE=InnoDB AUTO_INCREMENT=700001 DEFAULT CHARSET=utf8;

CREATE TRIGGER insert_createdBy_tblImageSubmission
BEFORE INSERT ON tbl_ImageSubmission
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tblImageSubmission
BEFORE UPDATE ON tbl_ImageSubmission
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_Pool(
PoolID INT AUTO_INCREMENT,
PoolName varchar(20) NOT NULL,
AssignID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_PoolID PRIMARY KEY(PoolID), 
CONSTRAINT fk_Pool_AssignID FOREIGN KEY(AssignID) REFERENCES tbl_Assignment(AssignID)); 

CREATE TRIGGER insert_createdBy_tbl_Pool
BEFORE INSERT ON tbl_Pool
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_Pool
BEFORE UPDATE ON tbl_Pool
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_PoolQuestion(
PoolQuestionID INT AUTO_INCREMENT,
DisplayOrder INT,
Question TEXT, 
PoolID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_PoolQuestionID PRIMARY KEY(PoolQuestionID), 
CONSTRAINT fk_PoolQuestion_PoolID FOREIGN KEY(PoolID) REFERENCES tbl_Pool(PoolID)); 

CREATE TRIGGER insert_createdBy_tbl_PoolQuestion
BEFORE INSERT ON tbl_PoolQuestion
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_PoolQuestion
BEFORE UPDATE ON tbl_PoolQuestion
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();



CREATE TABLE tbl_PoolQuestionFillin(
PoolQuestionFillinID INT AUTO_INCREMENT,
DisplayOrder INT,
Question TEXT, 
PoolQuestionID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_PoolQuestionFillinID PRIMARY KEY(PoolQuestionFillinID), 
CONSTRAINT fk_PoolQuestionFillin_PoolQuestionID FOREIGN KEY(PoolQuestionID) REFERENCES tbl_PoolQuestion(PoolQuestionID)); 

CREATE TRIGGER insert_createdBy_tbl_PoolQuestionFillin
BEFORE INSERT ON tbl_PoolQuestionFillin
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_PoolQuestionFillin
BEFORE UPDATE ON tbl_PoolQuestionFillin
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_PoolQuestionOption(
PoolQuestionOptionID INT AUTO_INCREMENT,
DisplayOrder INT,
Choice TEXT, 
FractionCorrect INT,
PoolQuestionID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_PoolQuestionOptionID PRIMARY KEY(PoolQuestionOptionID), 
CONSTRAINT fk_PoolQuestionOption_PoolQuestionID FOREIGN KEY(PoolQuestionID) REFERENCES tbl_PoolQuestion(PoolQuestionID)); 

CREATE TRIGGER insert_createdBy_tbl_PoolQuestionOption
BEFORE INSERT ON tbl_PoolQuestionOption
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_PoolQuestionOption
BEFORE UPDATE ON tbl_PoolQuestionOption
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();




CREATE TABLE tbl_StudentQuiz(
StudentQuizID INT AUTO_INCREMENT,
NumTakes INT,
StudentID INT,
QuizID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_StudentQuiz PRIMARY KEY(StudentQuizID), 
CONSTRAINT fk_StudentQuiz_StudentID FOREIGN KEY(StudentID) REFERENCES tbl_Student(StudentID),
CONSTRAINT fk_StudentQuiz_QuizID FOREIGN KEY(QuizID) REFERENCES tbl_Quiz(QuizID)
); 

CREATE TRIGGER insert_createdBy_tbl_StudentQuiz
BEFORE INSERT ON tbl_StudentQuiz
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_StudentQuiz
BEFORE UPDATE ON tbl_StudentQuiz
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();

CREATE TABLE tbl_StudentQuizTake(
StudentQuizTakeID INT AUTO_INCREMENT,
TakeNum INT,
StudentID INT,
QuizID INT,
CreatedDate timestamp default now(), 
UpdatedDate timestamp default now() on update now(),
CreatedBy varchar(50) default 'root',
UpdatedBy varchar(50) default 'root',
CONSTRAINT pk_StudentQuizTake PRIMARY KEY(StudentQuizTakeID), 
CONSTRAINT fk_StudentQuizTake_StudentID FOREIGN KEY(StudentID) REFERENCES tbl_Student(StudentID),
CONSTRAINT fk_StudentQuizTake_QuizID FOREIGN KEY(QuizID) REFERENCES tbl_Quiz(QuizID)
); 
CREATE TRIGGER insert_createdBy_tbl_StudentQuizTake
BEFORE INSERT ON tbl_StudentQuizTake
FOR EACH ROW
SET new.CreatedBy=CURRENT_USER();

CREATE TRIGGER update_updatedBy_tbl_StudentQuizTake
BEFORE UPDATE ON tbl_StudentQuizTake
FOR EACH ROW
SET new.UpdatedBy=CURRENT_USER();