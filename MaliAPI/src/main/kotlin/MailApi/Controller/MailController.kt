package MailApi.Controller

import MailApi.DTO.AttachmentDTO
import MailApi.DTO.MessageDTO
import MailApi.Services.EmailSenderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class MailController
{
    @Autowired
    private  lateinit var EmailSenderService : EmailSenderService
    @PostMapping("/sent")
    fun sendmail(@RequestBody messageDto: MessageDTO): ResponseEntity<MessageDTO>
    {
        EmailSenderService.sendSimpleEmail(messageDto.mailID,messageDto.message,messageDto.subject)
        return ResponseEntity.ok(messageDto)
    }
    @PostMapping("/sentFile")
    fun sendFiles(@RequestBody attachmentDTO: AttachmentDTO): ResponseEntity<AttachmentDTO>
    {
        EmailSenderService.sendEmailWithAttachment(attachmentDTO.mailID, attachmentDTO.message, attachmentDTO.subject, attachmentDTO.fileUrl)
        return ResponseEntity.ok(attachmentDTO)
    }


}