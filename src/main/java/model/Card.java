package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({
        "badges",
        "checkItemStates",
        "closed",
        "dueComplete",
        "dateLastActivity",
        "desc",
        "descData",
        "due",
        "dueReminder",
        "email",
        "idBoard",
        "idChecklists",
        "attachments",
        "idList",
        "idMembers",
        "idMembersVoted",
        "idShort",
        "idAttachmentCover",
        "labels",
        "idLabels",
        "manualCoverAttachment",
        "pos",
        "shortLink",
        "shortUrl",
        "subscribed",
        "stickers",
        "url",
        "cover",
        "isTemplate",
        "limits"
        })
public class Card {

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
