package chon.group.game.domain.environment;

import java.util.ArrayList;
import java.util.List;

import chon.group.game.domain.agent.Agent;
import javafx.scene.image.Image;

/**
 * Represents the game environment, including properties such as dimensions,
 * position,
 * background image, agents, and the protagonist.
 * The environment also controls rendering, restricts the environment area,
 * prints an agent's coordinates, and detects collisions between the protagonist
 * and agents.
 */
public class Environment {

    /** The X (horizontal) position of the environment. */
    private int posX;

    /** The Y (vertical) position of the environment. */
    private int posY;

    /** The width of the environment. */
    private int width;

    /** The height of the environment. */
    private int height;

    /** The background image of the environment. */
    private Image image;

    /** The background image of the pause. */
    private Image pauseImage;

    /** The background image of the pause. */
    private Image victoryImage;

    private Image imageGameOver;

    /** The protagonist instance. */
    private Agent protagonist;

    /** List of agents present in the environment. */
    private List<Agent> agents = new ArrayList<Agent>();

    /**
     * Default constructor to create an empty environment.
     */
    public Environment() {
    }

    /**
     * Constructor to initialize the environment with dimensions, position, and a
     * background image.
     *
     * @param posX      the initial X (horizontal) position of the environment
     * @param posY      the initial Y (vertical) position of the environment
     * @param width     the width of the environment
     * @param height    the height of the environment
     * @param pathImage the path to the background image
     */
    public Environment(int posX, int posY, int width, int height, String pathImage) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.setImage(pathImage);
        this.agents = new ArrayList<Agent>();
    }

    /**
     * Constructor to initialize the environment with dimensions, position, a
     * background image, and a list of agents.
     *
     * @param posX      the initial X (horizontal) position of the environment
     * @param posY      the initial Y (vertical) position of the environment
     * @param width     the width of the environment
     * @param height    the height of the environment
     * @param pathImage the path to the background image
     * @param agents    the list of agents in the environment
     */
    public Environment(int posX, int posY, int width, int height, String pathImage, ArrayList<Agent> agents) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.setImage(pathImage);
        this.setAgents(agents);
    }

    /**
     * Gets the X (horizontal) position of the environment.
     *
     * @return the X position of the environment
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the X (horizontal) position of the environment.
     *
     * @param posX the new X position of the environment
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Gets the Y (vertical) position of the environment.
     *
     * @return the Y position of the environment
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the Y (vertical) position of the environment.
     *
     * @param posY the new Y position of the environment
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Gets the width of the environment.
     *
     * @return the width of the environment
     */
    public int getWidth() {
        return width;
    }

    public Image getVictoryImage() {
        return victoryImage;
    }

    public void setImageGameOver(String imagePath) {
        this.imageGameOver = new Image(getClass().getResource(imagePath).toExternalForm());
    }

    public Image getImageGameOver() {
        return this.imageGameOver;
    }

    /**
     * Sets the background image of the environment.
     *
     * @param pathImage the path to the new background image
     */
    public void setImagevictory(String pathImage) {
        this.victoryImage = new Image(getClass().getResource(pathImage).toExternalForm());
    }

    /**
     * Sets the width of the environment.
     *
     * @param width the new width of the environment
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the environment.
     *
     * @return the height of the environment
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the environment.
     *
     * @param height the new height of the environment
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the background image of the environment.
     *
     * @return the background image of the environment
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the background image of the environment.
     *
     * @param pathImage the path to the new background image
     */
    public void setImage(String pathImage) {
        this.image = new Image(getClass().getResource(pathImage).toExternalForm());
    }

    public Image getPauseImage() {
        return pauseImage;
    }

    public void setPauseImage(String pathImage) {
        this.pauseImage = new Image(getClass().getResource(pathImage).toExternalForm());
    }

    /**
     * Gets the protagonist of the environment.
     *
     * @return the protagonist of the environment
     */
    public Agent getProtagonist() {
        return protagonist;
    }

    /**
     * Sets the protagonist of the environment.
     *
     * @param protagonist the new protagonist of the environment
     */
    public void setProtagonist(Agent protagonist) {
        this.protagonist = protagonist;
    }

    /**
     * Gets the list of agents present in the environment.
     *
     * @return the list of agents
     */
    public List<Agent> getAgents() {
        return agents;
    }

    /**
     * Sets the list of agents present in the environment.
     *
     * @param agents the new list of agents
     */
    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    /**
     * Checks if the protagonist is within the environment's boundaries and adjusts
     * its position if necessary.
     */
    public void checkBorders() {
        if (this.protagonist.getPosX() < 0) {
            this.protagonist.setPosX(0);
        } else if ((this.protagonist.getPosX() + this.protagonist.getWidth()) > this.width) {
            this.protagonist.setPosX(this.width - protagonist.getWidth());
        } else if (this.protagonist.getPosY() < 0) {
            this.protagonist.setPosY(0);
        } else if ((this.protagonist.getPosY() + this.protagonist.getHeight()) > this.height) {
            this.protagonist.setPosY(this.height - this.protagonist.getHeight());
        }
    }

    /**
     * Detects collisions between the protagonist and other agents in the
     * environment.
     */
    public void detectCollision() {
        for (Agent agent : this.agents) {
            if (protagonist != null && intersect(this.protagonist, agent)) {
                System.out.println("Collision detected with agent: " + agent);
                /* The protagonist takes damage when colliding with an agent. */
                protagonist.takeDamage(10);
            }
        }
    }

    /**
     * Checks if two agents collide with each other based on their positions and
     * dimensions.
     *
     * This method uses the coordinates and dimensions of both agents to determine
     * if their areas overlap. The collision is calculated by comparing the edges
     * of the image represented by each agent.
     *
     * @param a the first agent
     * @param b the second agent
     * @return true if the agents collide, otherwise false
     */

    private boolean intersect(Agent a, Agent b) {
        // Returns true if there is a collision between two agents
        return a.getPosX() < b.getPosX() + b.getWidth() &&
                a.getPosX() + a.getWidth() > b.getPosX() &&
                a.getPosY() < b.getPosY() + b.getHeight() &&
                a.getPosY() + a.getHeight() > b.getPosY();
    }

     /**
     * Defines the upper, lower, left, and right boundaries of the environment.
     * The agent's positions are adjusted to remain within these boundaries.
     */
    private static final int UPPER_LIMIT = 230; 
    private static final int LOWER_LIMIT = 580; 
    private static final int LEFT_LIMIT = 0;    
    private static final int RIGHT_LIMIT = 1280;

    /**
     * Adjusts an agent's position to ensure it stays within the defined boundaries.
     *
     * @param agent the agent whose position will be checked and adjusted.
     */
    public void checkAndAdjustPosition(Agent agent) {
        // Upper boundary
        if (agent.getPosY() < UPPER_LIMIT) {
            agent.setPosY(UPPER_LIMIT);
        }
        // Lower boundary
        if (agent.getPosY() > LOWER_LIMIT) {
            agent.setPosY(LOWER_LIMIT);
        }
        // Left boundary
        if (agent.getPosX() < LEFT_LIMIT) {
            agent.setPosX(LEFT_LIMIT);
        }
        // Right boundary
        if (agent.getPosX() > RIGHT_LIMIT) {
            agent.setPosX(RIGHT_LIMIT);
        }

        // Restricted special area: top-left corner
        if ((agent.getPosY() < 375) && (agent.getPosX() < 110)) {
            if (agent.getPosX() >= 105) {
                agent.setPosX(110);
            } else {
                agent.setPosY(375);
            }
        }

        // Restricted special area: top-right corner
        if ((agent.getPosY() < 405) && (agent.getPosX() > 1050)) {
            if (agent.getPosX() <= 1055) {
                agent.setPosX(1050);
            } else {
                agent.setPosY(405);
            }
        }
    }

    public void maintainDistance(Agent agent1, Agent agent2, double minDistance) {
        int dx = agent2.getPosX() - agent1.getPosX();
        int dy = agent2.getPosY() - agent1.getPosY();
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
    
        if (distance < minDistance && distance > 0) { // Evita divisão por zero
            // Calcula a direção de afastamento
            int adjustX = (int) ((dx / distance) * (minDistance - distance) * 1);
            int adjustY = (int) ((dy / distance) * (minDistance - distance) * 1);
    
            // Aplica o afastamento
            agent1.setPosX(agent1.getPosX() - adjustX);
            agent1.setPosY(agent1.getPosY() - adjustY);
            agent2.setPosX(agent2.getPosX() + adjustX);
            agent2.setPosY(agent2.getPosY() + adjustY);
        }
    }
}