package trust40.enforcer.rules;

import trust40.enforcer.PrivacyLevel;

import java.util.Objects;

public class DenyRule extends Rule {
    private PrivacyLevel privacyLevel;

    public DenyRule(String subject, String action, String object, PrivacyLevel privacyLevel) {
        super(subject, action, object);
        Objects.isNull(privacyLevel);
        this.privacyLevel = privacyLevel;
    }

    public PrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        DenyRule denyRule = (DenyRule) o;
        return privacyLevel.equals(denyRule.privacyLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), privacyLevel);
    }
}
