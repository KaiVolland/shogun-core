/**
 *
 */
package de.terrestris.shogun2.model.tree;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.terrestris.shogun2.model.PersistentObject;

/**
 * A class representing a node in a tree. This class can be used for leaf nodes.
 *
 * For folders, {@link TreeFolder} should be used.
 *
 * @author Nils Bühner
 * @author Kai Volland
 * @author terrestris GmbH & Co. KG
 *
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonInclude(Include.NON_NULL)
public class TreeNode extends PersistentObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The text to show on node label (html tags are accepted)
	 */
	private String text;

	/**
	 * Set to true to indicate that this child can have no children. The expand
	 * icon/arrow will then not be rendered for this node.
	 *
	 * As this class is used for leafs, we'll set the leaf property to true by default.
	 * {@link TreeFolder}s will set this property to false in the constructor.
	 */
	private boolean leaf = true;

	/**
	 * Set to true or false to show a checkbox alongside this node.
	 */
	private boolean checked;

	/**
	 * False to prevent expanding/collapsing of this node.
	 */
	private boolean expandable;

	/**
	 * True if the node is expanded.
	 */
	private boolean expanded;

	/**
	 * Path to an image to use as an icon.
	 */
	private String icon;

	/**
	 * One or more space separated CSS classes to be applied to the icon
	 * element. The CSS rule(s) applied should specify a background image to be
	 * used as the icon.
	 */
	private String iconCls;

	/**
	 * Tooltip text to show on this node.
	 */
	private String qTip;

	/**
	 * Tooltip title.
	 */
	private String qTitle;


	/**
	 * Explicitly adding the default constructor as this is important, e.g. for
	 * Hibernate: http://goo.gl/3Cr1pw
	 */
	public TreeNode() {
	}

	/**
	 *
	 */
	public TreeNode(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the expandable
	 */
	public boolean isExpandable() {
		return expandable;
	}

	/**
	 * @param expandable the expandable to set
	 */
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	/**
	 * @return the expanded
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/**
	 * @param expanded the expanded to set
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return the qTip
	 */
	public String getqTip() {
		return qTip;
	}

	/**
	 * @param qTip the qTip to set
	 */
	public void setqTip(String qTip) {
		this.qTip = qTip;
	}

	/**
	 * @return the qTitle
	 */
	public String getqTitle() {
		return qTitle;
	}

	/**
	 * @param qTitle the qTitle to set
	 */
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 *
	 *      According to
	 *      http://stackoverflow.com/questions/27581/overriding-equals
	 *      -and-hashcode-in-java it is recommended only to use getter-methods
	 *      when using ORM like Hibernate
	 */
	@Override
	public int hashCode() {
		// two randomly chosen prime numbers
		return new HashCodeBuilder(17, 5).
				appendSuper(super.hashCode()).
				append(getText()).
				append(isLeaf()).
				append(isChecked()).
				append(isExpandable()).
				append(isExpanded()).
				append(getIcon()).
				append(getIconCls()).
				append(getqTip()).
				append(getqTitle()).
				toHashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 *
	 *      According to
	 *      http://stackoverflow.com/questions/27581/overriding-equals
	 *      -and-hashcode-in-java it is recommended only to use getter-methods
	 *      when using ORM like Hibernate
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TreeNode))
			return false;
		TreeNode other = (TreeNode) obj;

		return new EqualsBuilder().appendSuper(super.equals(other)).
				append(getText(), other.getText()).
				append(isLeaf(), other.isLeaf()).
				append(isChecked(), other.isChecked()).
				append(isExpandable(), other.isExpandable()).
				append(isExpanded(), other.isExpanded()).
				append(getIcon(), other.getIcon()).
				append(getIconCls(), other.getIconCls()).
				append(getqTip(), other.getqTip()).
				append(getqTitle(), other.getqTitle()).
				isEquals();
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}