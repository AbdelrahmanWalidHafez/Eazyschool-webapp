package com.eazybytes.eazyschool.service;
import com.eazybytes.eazyschool.constants.Constants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Save Contact Details into DB
     *
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(Constants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return contactRepository.findByStatus(
                Constants.OPEN, pageable);
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
        int rows = contactRepository.updateStatusById(Constants.CLOSE, contactId);
        if (rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}