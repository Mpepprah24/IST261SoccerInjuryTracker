# Project Title:  'Soccer Injury Tracker'

* Student:  Michael Pepprah
* Instructor:  Ken Smith
* Class:  IST261 - App Development Studio I
* Semester:  Spring '26

Project Description:  A Java application for tracking soccer player injuries, medical staff information, teams, and recovery timelines. The system allows users to input and manage injury data to help coaches and medical staff monitor player health and recovery progress.


## Current Assignment Name:   'M02-A01: Identifying and Creating SW Model Classes'


## Assignment Accomplishments - (Description)

For this assignment, I created 5 model classes for my Soccer Injury Tracker project based on noun-verb analysis from my project proposal. Each class represents a key component of the injury tracking system:

1. **Player.java** - Represents a soccer player with attributes: name, age, position, team, and playerID
2. **Injury.java** - Represents an injury with attributes: injuryType, injuryDate, severity, and bodyPart
3. **MedicalStaff.java** - Represents medical personnel with attributes: staffID, name, role, specialization, and contactInfo
4. **Team.java** - Represents a soccer team with attributes: teamName, coach, location, league, and roster
5. **RecoveryTimeline.java** - Represents recovery progress with attributes: startDate, estimatedEndDate, currentPhase, and clearanceStatus

Each model class includes:
- 5 private fields (attributes) with appropriate visibility modifiers
- A parameterized constructor that initializes all fields
- Getter and setter methods for all attributes (accessors and mutators)
- An overridden toString() method that returns a String containing all instance data

The **Main.java** class demonstrates the functionality by:
- Instantiating one object of each model class using their parameterized constructors
- Calling the toString() method on each object to output initialized data to the console
- Using user input via Scanner to collect data for each object type
- Displaying a summary of all entered data at the end

Key design decisions:
- Only Player and MedicalStaff classes require ID fields (as specified in requirements)
- Team information is collected separately from player information
- Injury and RecoveryTimeline classes do not have ID fields to simplify data entry
- User-friendly prompts guide data entry with realistic examples (e.g., "ACL Tear" for injury type, "Sports Medicine" for specialization)

All code is organized in the `src/model/` package with Main.java at the root `src/` level. The project structure includes a `docs/` folder containing the required NounVerbAnalysis.pdf and this README.md file.


## Running the Project

These are instructions on how to run the project once loaded into IntelliJ.


### Steps

Step-by-step instructions on how to run your application.

1.  Main class to run for this assignment:
   
    Main.java

2.  Other specifics (if needed):
    
    - Run the program and follow the prompts to enter data for:
      - Player information (name, age, position, playerID)
      - Injury information (type, date, severity, body part)
      - Medical staff information (ID, name, role, specialization, contact)
      - Team information (name, coach, location, league, roster)
      - Recovery timeline (start date, end date, current phase, clearance status)
    - The program will display each created object and a final summary


 ##  Assignment Name: 'M03-A01: Writing Model Unit Tests'

## Assignment Accomplishments - (Description)

For this assignment, I implemented unit testing for my Soccer Injury Tracker project model classes using both manual testing with a TestHarness class and automated testing with JUnit.

### 1. Manual Unit Tests (TestHarness.java)

Created a `TestHarness` class that manually tests two model classes:

**Tested Classes:**
- **Player.java** - Tests all getters, setters, and toString() method
- **Injury.java** - Tests all getters, setters, and toString() method

**TestHarness Features:**
- Instantiates Player and Injury objects with sample data
- Tests all getter methods to verify proper initialization
- Tests all setter methods to verify proper value updates
- Tests toString() method to verify correct string representation
- Provides clear console output showing test results
- Includes error handling for potential test failures

### 2. JUnit Unit Tests

Created automated unit tests for two different model classes:

**Tested Classes:**
- **Team.java** - Created comprehensive JUnit tests for Galactic United (fictional team in Solar Premier League)
- **RecoveryTimeline.java** - Created comprehensive JUnit tests for injury recovery tracking

**JUnit Test Implementation:**
- **TeamTest.java** includes tests for:
  - getTeamName() and setTeamName()
  - getCoach() and setCoach()
  - getLocation() and setLocation()
  - getLeague() and setLeague()
  - getRoster() and setRoster()
  - testToString() method

- **RecoveryTimelineTest.java** includes tests for:
  - getStartDate() and setStartDate()
  - getEstimatedEndDate() and setEstimatedEndDate()
  - getCurrentPhase() and setCurrentPhase()
  - getClearanceStatus() and setClearanceStatus()
  - testToString() method

**Test Design Decisions:**
- Used `@BeforeEach` annotation to set up test objects before each test
- Created fictional "Galactic United" team with space-themed players for TeamTest
- Used realistic dates and recovery phases for RecoveryTimelineTest
- All JUnit tests pass successfully (100% pass rate)
- Each test method validates specific functionality with appropriate assertions

### 3. Project Structure Updates

Updated project structure to include test directories:
src/
├── main/
│ └── java/
│ └── model/
│ ├── Player.java
│ ├── Injury.java
│ ├── MedicalStaff.java
│ ├── Team.java
│ ├── RecoveryTimeline.java
│ └── TestHarness.java
└── test/
└── java/
└── model/
├── TeamTest.java
└── RecoveryTimelineTest.java

text

**Key Testing Principles Applied:**
- **Isolation**: Each test is independent and doesn't rely on other tests
- **Completeness**: All methods of tested classes are covered
- **Realism**: Tests use realistic data that reflects actual usage scenarios
- **Clarity**: Test names clearly indicate what functionality is being tested
- **Automation**: JUnit tests can be run automatically without manual intervention

**Technical Implementation Details:**
- Used JUnit 5 (Jupiter) annotations: `@Test`, `@BeforeEach`
- Used assertions: `assertEquals()`, `assertTrue()`
- Properly configured test directories in IntelliJ IDE
- Included necessary imports for JUnit framework
- All model classes updated with correct getter/setter signatures

---
## Current Assignment Name: 'M05-A01: Implementing a Simple MVC Application'

### Assignment Overview

For this assignment, I implemented a Model-View-Controller (MVC) architecture for the player management portion of my Soccer Injury Tracker application. The MVC pattern separates concerns into three distinct components, making the code more maintainable and scalable.

### MVC Implementation Details

#### Model Classes (2 classes)

| Class | Location | Purpose |
|-------|----------|---------|
| `Player.java` | `src/model/Player.java` | Represents a single player with all attributes (name, age, position, team, playerID, jersey number, preferred foot) |
| `PlayerList.java` | `src/model/PlayerList.java` | Contains an ArrayList of Player objects and manages list operations (navigation, CRUD, boundary checking) |

The `PlayerList` class automatically populates with 8 sample players:
- Lionel Messi (Inter Miami, #10)
- Lamine Yamal (FC Barcelona, #10)
- Mohammed Kudus (Tottenham Hotspur, #20)
- Florian Wirtz (Liverpool, #7)
- Desire Doue (PSG, #9)
- Scott McTominay (Napoli, #39)
- Rafael Leao (AC Milan, #10)
- Alessandro Bastoni (Inter Milan, #95)

#### Controller Class (1 class)

| Class | Location | Purpose |
|-------|----------|---------|
| `PlayerController.java` | `src/controller/PlayerController.java` | Mediates all communication between Model and View; handles business logic, navigation, validation, and CRUD operations |

The controller is responsible for:
- Creating and managing the model (`PlayerList`)
- Creating and displaying the view (`PlayerView`)
- Processing user actions from the view
- Updating the view with model data
- Enforcing boundary conditions (cannot go past first/last player)
- Input validation before adding/updating players

#### View Class (1 class)

| Class | Location | Purpose |
|-------|----------|---------|
| `PlayerView.java` | `src/view/PlayerView.java` | GUI interface with JTextFields and JButtons; communicates ONLY with the controller |

The view features:
- 7 JTextFields for player data entry (Name, Age, Position, Team, Player ID, Jersey #, Preferred Foot)
- 9 JButtons for all operations:
  - **First** - Navigate to first player
  - **Previous** - Navigate to previous player
  - **Next** - Navigate to next player
  - **Last** - Navigate to last player
  - **Add** - Add new player (with validation)
  - **Update** - Modify current player
  - **Delete** - Remove current player
  - **Clear** - Clear all text fields
  - **Exit** - Close application
- Status bar showing current position (e.g., "Player 1 of 8")
- Soccer-themed visual design with grass green background and emoji icons

#### Application Entry Point (1 class)

| Class | Location | Purpose |
|-------|----------|---------|
| `Main.java` | `src/Main.java` | Instantiates the controller, which in turn creates the model and view |

---

### Features Implemented

| Feature | Implementation | Boundary Checking |
|---------|----------------|-------------------|
| **Navigation** | First, Previous, Next, Last buttons | Buttons disable at list ends |
| **Add Player** | Creates new Player object and adds to list | Validates all fields are filled; checks number formats |
| **Update Player** | Modifies current player data | Cannot update if list is empty |
| **Delete Player** | Removes current player | Cannot delete from empty list; adjusts index after deletion |
| **Clear Fields** | Empties all text fields | Always available |
| **Exit** | Closes application | System.exit(0) called |

---

### Project Structure
IST261SoccerInjuryTracker/
├── src/ (BLUE - Sources Root)
│ ├── Main.java ← Application entry point
│ ├── TestHarness.java ← Manual tests from M03
│ ├── controller/ (BLUE) ← Controller package
│ │ └── PlayerController.java ← MVC Controller
│ ├── model/ (BLUE) ← Model package
│ │ ├── Person.java ← Super-class (M04)
│ │ ├── Player.java ← Model object
│ │ ├── PlayerList.java ← ArrayList container (NEW)
│ │ ├── MedicalStaff.java ← Sub-class (M04)
│ │ ├── Recoverable.java ← Interface (M04)
│ │ ├── Injury.java ← Model class (M02)
│ │ ├── RecoveryTimeline.java ← Model class (M02)
│ │ └── Team.java ← Model class (M02)
│ └── view/ (BLUE) ← View package
│ └── PlayerView.java ← MVC GUI View
├── test/ (GREEN - Test Sources Root)
│ └── test/
│ ├── TestInheritInterface.java ← M04 inheritance tests
│ └── model/
│ ├── RecoveryTimelineTest.java ← M03 JUnit tests
│ └── TeamTest.java ← M03 JUnit tests
└── docs/
└── README.md ← This file

text

---

### How to Run the Application

#### Option 1: Run the MVC Application
1. In IntelliJ, navigate to `src/Main.java`
2. Right-click → **Run 'Main.main()'**
3. The soccer-themed GUI window will appear
4. Use the buttons to navigate and manage players

#### Option 2: Run Previous Tests (Verify backward compatibility)
- **Manual Tests**: Run `src/TestHarness.java`
- **Inheritance Tests**: Run `test/test/TestInheritInterface.java`
- **JUnit Tests**: Right-click `test/` folder → **Run 'All Tests'**

---

### Testing the MVC Application

| Test Case | Expected Behavior | Status |
|-----------|-------------------|--------|
| Launch application | GUI opens with Lionel Messi's data displayed | ✅ |
| Click Next | Navigates to Lamine Yamal | ✅ |
| Click Previous | Returns to Lionel Messi | ✅ |
| Click First | Jumps to first player (Messi) | ✅ |
| Click Last | Jumps to last player (Bastoni) | ✅ |
| On first player | Previous/First buttons disabled | ✅ |
| On last player | Next/Last buttons disabled | ✅ |
| Add new player | Validates fields, adds to end, navigates to new player | ✅ |
| Update player | Modifies current player data | ✅ |
| Delete player | Removes current player, adjusts position | ✅ |
| Delete all players | Shows error when trying to delete from empty list | ✅ |
| Clear fields | Empties all text fields | ✅ |
| Exit button | Closes application | ✅ |

---

### Key Design Decisions

1. **Strict MVC Separation**
   - View has NO knowledge of Model
   - View ONLY communicates with Controller
   - Controller handles all business logic
   - Model manages data and list operations

2. **Boundary Protection**
   - Navigation buttons disable at list ends
   - Cannot delete from empty list
   - Cannot update when list is empty
   - Input validation before adding/updating

3. **User Experience**
   - Soccer-themed visual design with colors and emojis
   - Clear error messages with ❌ icon
   - Success messages with ✅ icon
   - Status bar shows current position

4. **Backward Compatibility**
   - All previous assignments still work
   - Original model classes unchanged
   - Tests still pass
   - Inheritance and interfaces intact

---
## Current Assignment Name: 'M06-A01: Implementing the List-Detail Pattern'

### Assignment Overview

For this assignment, I enhanced the existing MVC architecture to implement the list-detail pattern. The application now features a main list view with a scrollable JTable displaying all players, and a detail view dialog for adding and editing player information. All CRUD operations are fully implemented with proper validation and boundary checking.

### List-Detail Implementation

| Component | Class | Location | Purpose |
|-----------|-------|----------|---------|
| **Model** | `Player.java` | `src/model/Player.java` | Individual player data object with attributes |
| **Model (List)** | `PlayerList.java` | `src/model/PlayerList.java` | ArrayList container with CRUD operations and lookup methods |
| **Controller** | `PlayerController.java` | `src/controller/PlayerController.java` | Mediates between Model and Views, handles business logic |
| **List View** | `PlayerListView.java` | `src/view/PlayerListView.java` | Main window with scrollable JTable showing all players |
| **Detail View** | `PlayerView.java` | `src/view/PlayerView.java` | Dialog for adding/editing player details |

---

### Features Implemented

| Feature | Implementation Details | Status |
|---------|------------------------|--------|
| **Scrollable JTable** | 7 columns displaying player attributes with soccer-themed styling | ✅ |
| **Pre-populated Data** | 8 sample players loaded on application startup | ✅ |
| **4+ Attribute Columns** | ID, Name, Age, Position, Team, Jersey #, Preferred Foot (7 columns) | ✅ |
| **Select & View Details** | Double-click any row to open edit dialog with pre-filled data | ✅ |
| **Add New Player** | Detail dialog with validation for all fields | ✅ |
| **Edit Player** | Detail dialog pre-filled with existing data | ✅ |
| **Delete Player** | Confirmation dialog with prevention of empty list deletion | ✅ |
| **Sync Between Views** | Table automatically refreshes after add/update/delete operations | ✅ |
| **Exit Only from List View** | Exit button only available in main list view window | ✅ |
| **Boundary Checking** | Buttons disable appropriately when list is empty | ✅ |
| **Duplicate Prevention** | Cannot add player with existing ID | ✅ |
| **Input Validation** | All fields required; age and jersey must be valid numbers | ✅ |

---

### Sample Players (8 Players Pre-loaded)

| Name | Age | Position | Team | Jersey # | Player ID | Foot |
|------|-----|----------|------|----------|-----------|------|
| Lionel Messi | 38 | Right Winger | Inter Miami | 10 | MESSI010 | Left |
| Lamine Yamal | 18 | Right Winger | FC Barcelona | 10 | YAMAL027 | Left |
| Mohammed Kudus | 25 | Midfielder | Tottenham Hotspur | 20 | KUDUS020 | Right |
| Florian Wirtz | 22 | Midfielder | Liverpool | 7 | WIRTZ007 | Right |
| Desire Doue | 20 | Right Winger | PSG | 9 | DOUE009 | Right |
| Scott McTominay | 29 | Midfielder | Napoli | 39 | MCTOMINAY039 | Right |
| Rafael Leao | 26 | Left Winger | AC Milan | 10 | LEAO010 | Right |
| Alessandro Bastoni | 26 | Center Back | Inter Milan | 95 | BASTONI095 | Left |

---

### Soccer-Themed UI Elements

| Element | Design Choice |
|---------|---------------|
| **Background** | Grass green (#2E7D32) |
| **Buttons** | Light green (#4CAF50) with white borders |
| **Table Header** | Light green background |
| **Status Bar** | Soil brown (#795548) |
| **Icons/Emojis** | ⚽, 👤, 🎂, ⚙️, 🏟️, 🆔, 🔢, 👟, ➕, ✏️, ❌, 🔄, 🚪 |
| **Messages** | ❌ for errors, ✅ for success, ❓ for confirmations |

---

### How to Run the Application

1. **In IntelliJ**, navigate to `src/Main.java`
2. **Right-click** → **Run 'Main.main()'**
3. The main **Player List** window will appear with the JTable showing all players
4. From here you can:
   - **Browse players** using the scrollable table
   - **Select** a player by clicking any row
   - **Edit** by double-clicking a row or selecting then clicking "Edit Selected"
   - **Add** new players using the "Add Player" button
   - **Delete** selected players with confirmation
   - **Refresh** the table view
   - **Exit** the application

---

### CRUD Operations Walkthrough

#### Create (Add Player)
1. Click **"➕ Add Player"** button
2. Detail dialog opens with empty fields
3. Fill in all player information
4. Click **"➕ Add"** button
5. Validation occurs:
   - All fields must be filled
   - Age and Jersey must be valid numbers
   - Player ID must be unique
6. On success, dialog closes and table refreshes showing new player

#### Read (View Players)
- All players visible in scrollable JTable
- 7 columns show complete player information
- Table is read-only to prevent accidental edits
- Double-click any row to view full details in edit mode

#### Update (Edit Player)
- **Method 1:** Double-click any row in the table
- **Method 2:** Select row → Click **"✏️ Edit Selected"**
- Detail dialog opens with all fields pre-filled
- Make changes and click **"✏️ Update"**
- Validation ensures:
  - All fields filled
  - If ID changed, new ID must be unique
- Table automatically refreshes with updated data

#### Delete (Remove Player)
1. Select a player row
2. Click **"❌ Delete Selected"**
3. Confirmation dialog appears
4. Click **"Yes"** to confirm deletion
5. Player removed from list
6. Table refreshes automatically
7. If list becomes empty, edit/delete buttons disable

---

### Boundary Conditions & Validation

| Scenario | Application Behavior |
|----------|---------------------|
| **Empty list** | Edit/Delete buttons disabled; cannot delete |
| **No row selected** | Warning message when trying to edit/delete |
| **Duplicate Player ID** | Error message; prevents addition |
| **Missing fields** | Error message; requires all fields |
| **Invalid age/jersey** | Error message; must be valid numbers |
| **Delete confirmation** | Yes/No dialog prevents accidental deletion |
| **At list start/end** | Navigation buttons handled by controller |

---

### Project Structure
IST261SoccerInjuryTracker/
├── src/ (BLUE - Sources Root)
│ ├── Main.java ← Application entry point
│ ├── TestHarness.java ← Manual tests (M03)
│ ├── controller/ (BLUE)
│ │ └── PlayerController.java ← MVC Controller (updated for M06)
│ ├── model/ (BLUE)
│ │ ├── Person.java ← Super-class (M04)
│ │ ├── Player.java ← Model object
│ │ ├── PlayerList.java ← ArrayList container (updated for M06)
│ │ ├── MedicalStaff.java ← Sub-class (M04)
│ │ ├── Recoverable.java ← Interface (M04)
│ │ ├── Injury.java ← Model class (M02)
│ │ ├── RecoveryTimeline.java ← Model class (M02)
│ │ └── Team.java ← Model class (M02)
│ └── view/ (BLUE)
│ ├── PlayerListView.java ← NEW! List View with JTable (M06)
│ └── PlayerView.java ← Detail View Dialog (updated for M06)
├── test/ (GREEN - Test Sources Root)
│ └── test/
│ ├── TestInheritInterface.java ← M04 inheritance tests
│ └── model/
│ ├── RecoveryTimelineTest.java ← M03 JUnit tests
│ └── TeamTest.java ← M03 JUnit tests
└── docs/
└── README.md 
# Project Title: 'Soccer Injury Tracker'

* Student: [Your Name]
* Instructor: Ken Smith
* Class: IST261 - App Development Studio I
* Semester: Spring '26

Project Description: A Java application for tracking soccer player injuries, medical staff information, teams, and recovery timelines. The system implements MVC architecture, list-detail pattern, and data persistence using Java object serialization to save and load player and team data between application runs.

---

## Current Assignment Name: 'M07-A01: Persistent Data Strategy and Implementation'

### Assignment Overview

For this assignment, I implemented a complete data persistence strategy using Java Object Serialization. The application now saves all player and team data between application runs, ensuring that user-added, edited, and deleted data persists across sessions.

### Persistence Implementation Details

#### Model Classes Persisted

**Class 1: Player** (`src/model/Player.java`)

| Field | Type | Description |
|-------|------|-------------|
| name | String | Player's full name |
| age | int | Player's age in years |
| playerID | String | Unique identifier |
| position | String | Playing position |
| team | String | Current team name |
| jerseyNumber | int | Jersey number |
| preferredFoot | String | Left/Right foot preference |

**Why Persist:** Players are the core entities in the application. All injuries, recovery timelines, and medical records are associated with players. Persisting player data ensures rosters are maintained across sessions.

**Class 2: Team** (`src/model/Team.java`)

| Field | Type | Description |
|-------|------|-------------|
| teamName | String | Name of the soccer team |
| coach | String | Head coach of the team |
| location | String | City/region where team is based |
| league | String | League the team competes in |
| roster | String | Comma-separated list of player names |

**Why Persist:** Teams provide organizational context for players. Team information (coach, location, league) is important for injury tracking and player management. Persisting team data maintains accurate information across sessions.

#### Controller Class for Persistence

**PlayerController** (`src/controller/PlayerController.java`)

| Method | Purpose |
|--------|---------|
| `createDataDirectory()` | Creates the /data directory if it doesn't exist |
| `saveData()` | Saves both PlayerList and TeamList to files |
| `setupShutdownHook()` | Registers auto-save on application exit |
| `loadFromFile()` | Called by PlayerList/TeamList to load saved data |

#### Supporting Classes for Persistence

| Class | Location | Purpose |
|-------|----------|---------|
| `PlayerList` | `src/model/PlayerList.java` | Container for Player objects with save/load methods |
| `TeamList` | `src/model/TeamList.java` | Container for Team objects with save/load methods |
| `Player` | `src/model/Player.java` | Implements Serializable |
| `Team` | `src/model/Team.java` | Implements Serializable |
| `Person` | `src/model/Person.java` | Super-class, implements Serializable |

### Persistence Strategy

**Storage Location:** `/data` directory at project root (auto-created on first run)

**File Structure:**
/data/
├── players.dat ← Serialized PlayerList object
└── teams.dat ← Serialized TeamList object

text

**Serialization Process:**
- All persisted classes implement `java.io.Serializable`
- Each class has a `serialVersionUID` for version control
- `ObjectOutputStream` writes objects to .dat files
- `ObjectInputStream` reads objects from .dat files
- Shutdown hook ensures data saves even if user closes window

**Fallback Strategy:**
- If no save files exist on first run, default data is created
- Default data includes 8 players and 10 teams
- On subsequent runs, saved data is loaded instead of defaults

---

### GUI Views for Persistence Demonstration

| View | Purpose | Persistence Interaction |
|------|---------|------------------------|
| `PlayerListView` | Main window displaying all players in JTable | Shows loaded data; triggers save on add/edit/delete |
| `TeamListView` | Secondary window displaying all teams in JTable | Shows loaded data; triggers save on add/edit/delete |
| `PlayerView` | Dialog for adding/editing player details | Updates model; controller saves |
| Team Dialog | Embedded dialog for adding/editing teams | Updates model; controller saves |

---

### How to Demonstrate Data Persistence to Users

#### Step 1: First Application Run
1. Launch `Main.java`
2. Both Player List and Team List windows appear
3. Initial data loads automatically (8 players, 10 teams)
4. User can verify default data in both tables

#### Step 2: Make Changes to Data
5. **Add a Player:**
   - Click "Add Player" button
   - Enter new player information (e.g., "Erling Haaland", Age 24, etc.)
   - Click "Add"
   - New player appears in table

6. **Edit a Team:**
   - Double-click a team in Team List window
   - Change coach name (e.g., "Pep Guardiola" → "New Coach")
   - Click "Update"
   - Team information updates in table

7. **Delete a Player:**
   - Select a player row in Player List
   - Click "Delete Selected"
   - Confirm deletion
   - Player removed from table

#### Step 3: Exit and Restart
8. Click "Exit" button in Player List window
9. Application closes; data auto-saves to `/data/players.dat` and `/data/teams.dat`
10. Relaunch the application by running `Main.java` again

#### Step 4: Verify Persistence
11. Player List window opens with new player (Haaland) still present
12. Deleted player is gone
13. Team List window shows updated coach name
14. All changes from previous session are preserved

---

### Sample Data Pre-populated

#### Players (8 default players)

| Name | Age | Position | Team | Jersey # | Player ID | Foot |
|------|-----|----------|------|----------|-----------|------|
| Lionel Messi | 38 | Right Winger | Inter Miami | 10 | MESSI010 | Left |
| Lamine Yamal | 18 | Right Winger | FC Barcelona | 10 | YAMAL027 | Left |
| Mohammed Kudus | 25 | Midfielder | Tottenham Hotspur | 20 | KUDUS020 | Right |
| Florian Wirtz | 22 | Midfielder | Liverpool | 7 | WIRTZ007 | Right |
| Desire Doue | 20 | Right Winger | PSG | 9 | DOUE009 | Right |
| Scott McTominay | 29 | Midfielder | Napoli | 39 | MCTOMINAY039 | Right |
| Rafael Leao | 26 | Left Winger | AC Milan | 10 | LEAO010 | Right |
| Alessandro Bastoni | 26 | Center Back | Inter Milan | 95 | BASTONI095 | Left |

#### Teams (10 default teams)

| Team Name | Coach | Location | League | Key Players |
|-----------|-------|----------|--------|-------------|
| SSC Napoli | Antonio Conte | Naples, Italy | Serie A | McTominay, Di Lorenzo, Meret |
| Inter Milan | Cristian Chivu | Milan, Italy | Serie A | Martinez, Barella, Thuram |
| Manchester City | Pep Guardiola | Manchester, England | Premier League | Haaland, Foden, Dias |
| Real Madrid | Alvaro Arbeloa | Madrid, Spain | La Liga | Mbappe, Valverde, Vinicius Jr. |
| FC Barcelona | Hansi Flick | Barcelona, Spain | La Liga | Yamal, Lewandowski, Pedri |
| Paris Saint-Germain | Luis Enrique | Paris, France | Ligue 1 | Dembele, Kvaratskhelia, Barcola |
| Bayern Munich | Vincent Kompany | Munich, Germany | Bundesliga | Kane, Olise, Diaz |
| Arsenal | Mikel Arteta | London, England | Premier League | Saka, Odegaard, Gyokeres |
| Liverpool | Arne Slot | Liverpool, England | Premier League | Salah, van Dijk, Wirtz |
| Juventus | Luciano Spalletti | Turin, Italy | Serie A | Vlahovic, Yildiz, McKennie |

---

### Project Structure
IST261SoccerInjuryTracker/
├── src/
│ ├── Main.java ← Application entry point
│ ├── controller/
│ │ └── PlayerController.java ← MVC Controller (persistence manager)
│ ├── model/
│ │ ├── Person.java ← Super-class (Serializable)
│ │ ├── Player.java ← Model object (Serializable)
│ │ ├── PlayerList.java ← Container with save/load
│ │ ├── Team.java ← Model object (Serializable)
│ │ ├── TeamList.java ← Container with save/load
│ │ └── (other model classes)
│ └── view/
│ ├── PlayerListView.java ← Main list view
│ ├── PlayerView.java ← Detail view dialog
│ └── TeamListView.java ← Team list view
├── data/ ← Persistence storage (auto-created)
│ ├── players.dat ← Serialized player data
│ └── teams.dat ← Serialized team data
├── test/
│ └── test/
│ ├── TestInheritInterface.java
│ └── model/
│ ├── RecoveryTimelineTest.java
│ └── TeamTest.java
└── docs/
├── README.md ← This file
└── PersistentDataStrategy_M07_A01.pdf ← Persistence strategy document

text

---

### How to Run the Application

1. **Open IntelliJ IDEA**
2. Navigate to `src/Main.java`
3. Right-click → **Run 'Main.main()'**
4. Two windows will appear:
   - **Player List Window** (⚽) - Shows all players in a table
   - **Team List Window** (🏆) - Shows all teams in a table
5. Use buttons to add, edit, or delete data
6. Click **Exit** to save and close

---

### Testing Persistence

| Test | Expected Result | Status |
|------|-----------------|--------|
| Add new player, exit, restart | New player appears in table | ✅ |
| Edit team, exit, restart | Edited team data persists | ✅ |
| Delete player, exit, restart | Deleted player is gone | ✅ |
| First run with no data | Default data loads | ✅ |
| Delete all players | Delete button disables | ✅ |
| Duplicate player ID | Error message shown | ✅ |

---

### Previous Assignments Preserved

| Assignment | Description | Status |
|------------|-------------|--------|
| M02-A01 | Model Classes (Player, Injury, MedicalStaff, Team, RecoveryTimeline) | ✅ Preserved |
| M03-A01 | Unit Testing (TestHarness, JUnit tests) | ✅ Preserved |
| M04-A01 | Inheritance (Person → Player, MedicalStaff) & Interfaces (Recoverable) | ✅ Preserved |
| M05-A01 | MVC Architecture (Controller, View, Model) | ✅ Enhanced |
| M06-A01 | List-Detail Pattern (JTable with list and detail views) | ✅ Enhanced |
| M07-A01 | Data Persistence (Serialization, /data directory) | ✅ NEW |

---

### Known Limitations

- Data is not encrypted (plain serialization)
- No backup or versioning of save files
- Only Player and Team objects are persisted
- File paths are hardcoded to /data directory

---

### Submission Information

**Date:** March 26, 2026  
**Assignment:** M07-A01: Persistent Data Strategy and Implementation  
**ZIP File Name:** `IST261_M07A01_[YourLastName].zip`

**Contents of ZIP:**
- `src/` - All source code with persistence implementation
- `data/` - Data directory (will be empty initially, created on first run)
- `test/` - All test files
- `docs/` - README.md and PersistentDataStrategy_M07_A01.pdf

**All code compiles and runs without errors.** ✅

---
## Current Assignment Name: 'M08-A01: Implementing a LinkedList'

### LinkedList Implementation

For this assignment, I implemented a sorted LinkedList of Player objects to demonstrate the LinkedList data structure from the Java Collections Framework. The LinkedList is sorted by **jersey number** in ascending order.

#### New Classes Created

| Class | Location | Purpose |
|-------|----------|---------|
| `PlayerNode` | `src/model/PlayerNode.java` | Wrapper class representing a player item in the LinkedList |
| `SortedPlayerList` | `src/model/SortedPlayerList.java` | Container class that manages the LinkedList and its operations |

#### Sorted By Attribute

**The LinkedList is sorted by: JERSEY NUMBER (ascending order)**

This is a natural sorting attribute because:
- Jersey numbers are unique identifiers
- They sort numerically (lowest to highest)
- Adding/removing players requires finding the correct position

#### Features Implemented

| Method | Description | Demonstration |
|--------|-------------|---------------|
| `addItem(PlayerNode)` | Adds a new player to the LinkedList in sorted order by jersey number | Added player with jersey #15 (inserted between #20 and #10) |
| `removeItem(String)` | Removes a player by jersey number | Removed player with jersey #10 (multiple #10s exist, removes first) |
| `getItem(String)` | Finds a player by jersey number | Searched for jersey #20, found Mohammed Kudus |
| `printList()` | Prints all items in the LinkedList | Displays current list after each operation |

#### Test Data (8 Initial Players)

| Jersey # | Player Name | Position | Team |
|----------|-------------|----------|------|
| 95 | Alessandro Bastoni | Center Back | Inter Milan |
| 39 | Scott McTominay | Midfielder | Napoli |
| 20 | Mohammed Kudus | Midfielder | Tottenham Hotspur |
| 10 | Rafael Leao | Left Winger | AC Milan |
| 10 | Lamine Yamal | Right Winger | FC Barcelona |
| 10 | Lionel Messi | Right Winger | Inter Miami |
| 9 | Desire Doue | Right Winger | PSG |
| 7 | Florian Wirtz | Midfielder | Liverpool |

#### Demonstrations

1. **Add Item:** Added Erling Haaland (jersey #15) - inserted in the middle between #20 and #10
2. **Get Item:** Searched for jersey #20 - found Mohammed Kudus
3. **Remove Item:** Removed Rafael Leao (jersey #10) - removed from the middle
4. **Additional Add:** Added Jude Bellingham (jersey #5) - inserted at the beginning

#### How to Run

1. Navigate to `src/M08LinkedListDemo.java`
2. Right-click → **Run 'M08LinkedListDemo.main()'**
3. View console output showing:
   - Initial list of 8 players sorted by jersey number
   - Add operation demonstration
   - Get operation demonstration
   - Remove operation demonstration
   - Final list showing correct sorted order

#### Key Learning Outcomes

- LinkedList maintains insertion order
- Items added in sorted order using ListIterator to find correct position
- Removing items from the middle of the list demonstrates LinkedList efficiency
- Multiple players can share the same jersey number (handled by insertion order)

---
## Current Assignment Name: 'M08-A02: Identify and Implement a Collection (HashMap)'

### Assignment Overview

For this assignment, I implemented a **HashMap** to manage player injuries. The HashMap maps a Player ID (String key) to an ArrayList of Injury objects (value). This collection type was chosen for its O(1) lookup time, which is critical for quickly accessing a player's injury history.

### Collection Type Chosen: HashMap

| Feature | Implementation |
|---------|----------------|
| **Key** | Player ID (String) - unique identifier for each player |
| **Value** | ArrayList&lt;Injury&gt; - list of injuries for that player |
| **Lookup Time** | O(1) - constant time performance |
| **Insertion Time** | O(1) - constant time for adding new injuries |
| **Deletion Time** | O(1) - constant time for removing injuries |

### Why HashMap Was Chosen

HashMap was selected over other collection types for the following reasons:

| Collection | Performance | Why Not Chosen |
|------------|-------------|----------------|
| **ArrayList** | O(n) search | Linear search would be too slow as more players are added |
| **TreeMap** | O(log n) lookup | Slower than HashMap's O(1) for this use case |
| **HashSet** | Cannot store multiple values per key | Each player needs to track multiple injuries |
| **LinkedList** | O(n) search | Would require sequential traversal to find players |

**Key Advantages of HashMap:**
1. O(1) lookup time for instant player injury history access
2. Unique keys match natural player ID constraint
3. Supports multiple values per key through ArrayList values
4. Fast insertion and deletion for real-time injury tracking

### New Classes Created for M08-A02

| Class | Location | Purpose |
|-------|----------|---------|
| `InjuryManager` | `src/model/InjuryManager.java` | HashMap wrapper with CRUD operations for injuries |
| `InjuryController` | `src/controller/InjuryController.java` | MVC Controller for injury management |
| `InjuryManagementView` | `src/view/InjuryManagementView.java` | GUI with tabs for viewing and managing injuries |

### Updated Classes for M08-A02

| Class | Location | Changes Made |
|-------|----------|--------------|
| `Injury` | `src/model/Injury.java` | Added 7-parameter constructor with playerId field |
| `TestHarness` | `src/TestHarness.java` | Updated to use new Injury constructor |

### Features Implemented

| Feature | How to Use | Collection Method | Time Complexity |
|---------|------------|-------------------|-----------------|
| **View Player Injuries** | Enter Player ID → Click "View Injuries" | `HashMap.get()` | O(1) |
| **Search Injuries** | Enter Player ID + search term | `HashMap.get()` + linear search | O(n) |
| **Add Injury** | Fill form → Click "Add Injury" | `HashMap.put()` | O(1) |
| **Delete Injury** | Enter Player ID + injury number | `HashMap.get()` + `ArrayList.remove()` | O(1) |
| **View Statistics** | Click Statistics tab | `HashMap.keySet()` and `values()` | O(n) |

### Sample Data (8 Injuries Pre-loaded)

| Player ID | Player Name | Injury Type | Severity | Date |
|-----------|-------------|-------------|----------|------|
| MESSI010 | Lionel Messi | Hamstring Strain | Medium | 2024-02-10 |
| MESSI010 | Lionel Messi | Ankle Sprain | Low | 2024-01-15 |
| YAMAL027 | Lamine Yamal | Knee Contusion | Medium | 2024-03-01 |
| WIRTZ007 | Florian Wirtz | ACL Tear | High | 2024-01-05 |
| KUDUS020 | Mohammed Kudus | Ankle Sprain | Low | 2024-02-20 |
| MCTOMINAY039 | Scott McTominay | Groin Strain | Medium | 2024-03-10 |
| LEAO010 | Rafael Leao | Shoulder Dislocation | High | 2024-02-28 |
| BASTONI095 | Alessandro Bastoni | Calf Strain | Low | 2024-03-05 |

### How to Run the HashMap Demo

1. **Run `Main.java`**
2. The Player Management System will launch first
3. A dialog will ask: "Would you like to also launch the Injury Management System?"
4. Click **Yes** to launch the HashMap demo
5. The Injury Management window will appear with 2 tabs:
   - **View Injuries** - Enter a Player ID to see all injuries (O(1) lookup)
   - **Statistics** - View total players with injuries and total injuries count

### HashMap Performance Demonstration

| Operation | Players in System | Time Complexity | Demonstrated |
|-----------|-------------------|-----------------|--------------|
| Lookup player injuries | 8+ | O(1) - instant | ✓ Yes |
| Add new injury | Any | O(1) - instant | ✓ Yes |
| Delete injury | Any | O(1) - instant | ✓ Yes |
| Search by injury type | 8+ | O(n) - linear | ✓ Yes |

### GUI Tabs Explained

**Tab 1: View Injuries**
- Enter a Player ID (e.g., "MESSI010", "YAMAL027", "WIRTZ007")
- Click "View Injuries" button
- Displays all injuries for that player in a formatted list
- Shows injury type, date, and severity

**Tab 2: Statistics**
- Shows total number of players with injuries
- Shows total number of injuries recorded
- Refresh button updates statistics

---