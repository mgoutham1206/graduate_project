Insert into tbl_user(LoginName,
UserPassword,
Name,
EmailAddress) values('geoapp3','welcome','Geouser','geoapp@mail.nwmissouri.edu');

Insert into tbl_user(LoginName,
UserPassword,
Name,
EmailAddress) values('geoappIns3','welcome','GeouserIns','geoapp@mail.nwmissouri.edu');

insert into tbl_UserRole(UserID,RoleTypeID)
values(100011,1);



insert into tbl_UserRole(UserID,RoleTypeID)
values(100012,3);

insert into tbl_section(	Title,
	Year,
	TermID,
	Basicdescription,
	ClassURL,
	ExpectedNoofStudents,
	EnrollmentPassword,
	AllowEnrollement) 
values ('SectionCharlesHoot1',2016,2,'Testing','vfnslj',23,'testing','Y');

Insert into tbl_Student(StudentID,
SectionID,
IsActive) values (100011,200003,'Y');
	
	
	Insert into tbl_InstructorSection(InstructorID,SectionID) 
	values (100012,200003);
	
	
	Insert into tbl_Quiz (QuizName,QuizDesc,NumQuestions,Num_Takes_Max)
Values('test2','Testing entry for quiz',5,12);


insert into tbl_assignment(QuizID,
	SectionID,
	Name,
	Due_date,
	Description,
	Possiblepointsphase1,
	Possiblepointsphase2,
	Possiblepointsphase3,
	IsActive) 
values(300004,200004,'Assignment1','2016-02-12','test',5.23,7.34,4.56,'Y');
	
	
	
insert into tbl_submission(StudentID,AssignmentID,Description,RockType,RockName) 
values(100011,400004,'Testing submission','Metamorphic','Granite');

insert into tbl_MineralSubmission(SubmissionID,
	Luster,
	Hardness,
	StreakColor,
	SpecificGravity,
	Geometry,
	Color,
	Name,
	DisplayOrder)
values(500005,'lustervalue','3.0','brown','9.8','Geometryvalue','Red','Rock',1);

insert into tbl_AttributeEvaluation(MineralID,
	AttributeID,
	IsAccepted,
	Comments)
values(600006,4,'Y','attribute evaluation example');


insert into tbl_PhaseEvaluation(SubmissionID,
	Phase,
	IsAccepted,
	Comments,
	Points) 
values(500005,2,'Y','Phase eval done',23.5);

insert into tbl_imagesubmission(SubmissionID,ImageLocation,Latitude,Longitude,ImageOrder,IsBest,Image,IsShow) 
values (500005,'Maryville','-3.234','2.345',1,'Y',LOAD_FILE('C:\geoappdb\download.jpg'),'N');

update tbl_imagesubmission set ImageLocation = 'New York' 
where ImageID = 700004;

update tbl_imagesubmission set Latitude = 79.45657
where ImageID = 700004;

update tbl_imagesubmission set 
 longitude = -98.097
where ImageID = 700004;


